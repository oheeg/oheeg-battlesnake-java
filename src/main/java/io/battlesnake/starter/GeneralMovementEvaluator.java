package io.battlesnake.starter;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

class GeneralMovementEvaluator
{
  private final JsonNode moveRequest;

  GeneralMovementEvaluator( final JsonNode moveRequest )
  {
    this.moveRequest = moveRequest;
  }

  List<String> checkPossibleDirections()
  {
    final List<String> possibleDirections = new ArrayList<>();
    possibleDirections.add("up");
    possibleDirections.add("down");
    possibleDirections.add("right");
    possibleDirections.add("left");

    if (!canGoDown())
    {
      possibleDirections.remove("down");
    }
    if (!canGoLeft())
    {
      possibleDirections.remove("left");
    }
    if (!canGoRight())
    {
      possibleDirections.remove("right");
    }
    if (!canGoUp())
    {
      possibleDirections.remove("up");
    }
    return possibleDirections;
  }

  private boolean canGoRight()
  {
    final boolean willBite = willBiteOwnBodyInDirection("right");

    return !rightWallReached() && !willBite && !upperRightCornerReached() && !lowerRightCornerReached();
  }

  private boolean canGoLeft()
  {
    final boolean willBite = willBiteOwnBodyInDirection("left");

    return !leftWallReached() && !willBite && !upperLeftCornerReached() && !lowerLeftCornerReached();
  }

  private boolean canGoUp()
  {
    final boolean willBite = willBiteOwnBodyInDirection("up");

    return !upperWallReached() && !willBite && !upperLeftCornerReached() && !upperRightCornerReached();
  }

  private boolean canGoDown()
  {
    final boolean willBite = willBiteOwnBodyInDirection("down");

    return !lowerWallReached() && !willBite && !lowerLeftCornerReached() && !lowerRightCornerReached();
  }

  private boolean rightWallReached()
  {
    final int width = moveRequest.get("board").get("width").asInt();
    final int currentX = getSnakeBody().get(0).get("x").asInt();

    if (currentX == width - 1)
    {
      System.out.println("Right wall reached");
    }

    return currentX == width - 1;
  }

  private boolean leftWallReached()
  {
    final int currentX = getSnakeBody().get(0).get("x").asInt();

    if (currentX == 0)
    {
      System.out.println("Left wall reached");
    }

    return currentX == 0;
  }

  private boolean upperWallReached()
  {
    final int currentY = getSnakeBody().get(0).get("y").asInt();

    if (currentY == 0)
    {
      System.out.println("Upper wall reached");
    }

    return currentY == 0;
  }

  private boolean lowerWallReached()
  {
    final int height = moveRequest.get("board").get("height").asInt();
    final int currentY = getSnakeBody().get(0).get("y").asInt();

    if (currentY == height - 1)
      System.out.println("Lower wall reached");

    return currentY == height - 1;
  }

  private boolean lowerRightCornerReached()
  {
    return lowerWallReached() && rightWallReached();
  }

  private boolean upperRightCornerReached()
  {
    return upperWallReached() && rightWallReached();
  }

  private boolean upperLeftCornerReached()
  {
    return upperWallReached() && leftWallReached();
  }

  private boolean lowerLeftCornerReached()
  {
    return lowerWallReached() && leftWallReached();
  }

  private boolean willBiteOwnBodyInDirection( final String desiredDirection )
  {
    final JsonNode snakeBody = getSnakeBody();

    boolean willBite = false;

    final int currentX = snakeBody.get(0).get("x").asInt();
    final int lastX = snakeBody.get(1).get("x").asInt();

    final int currentY = snakeBody.get(0).get("y").asInt();
    final int lastY = snakeBody.get(1).get("y").asInt();

    switch (desiredDirection)
    {
      case "right":
        willBite = currentX < lastX;
        break;
      case "left":
        willBite = currentX > lastX;
        break;
      case "up":
        willBite = currentY > lastY;
        break;
      case "down":
        willBite = currentY < lastY;
        break;
    }
    if (willBite)
    {
      System.out.println("Will bite for " + desiredDirection);
    }
    return willBite;
  }

  private JsonNode getSnakeBody()
  {
    return moveRequest.get("you").get("body");
  }
}

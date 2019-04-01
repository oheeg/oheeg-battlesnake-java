package io.battlesnake.starter;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.HashMap;
import java.util.Map;

class FoodLocator
{

  private final JsonNode moveRequest;
  private final Map<Integer, Integer> foodLocations = new HashMap<>();

  FoodLocator( final JsonNode moveRequest )
  {
    this.moveRequest = moveRequest;
  }

  Map<Integer, Integer> getAllAvailableFoodPositions()
  {
    final JsonNode food = moveRequest.get("board").get("food");

    for (int i = 0; i < food.size(); i++)
    {
      final JsonNode element = food.get(i);
      foodLocations.put(element.get("x").asInt(), element.get("y").asInt());
    }
    return foodLocations;
  }
}

package io.battlesnake.operations;

import com.fasterxml.jackson.databind.JsonNode;
import io.battlesnake.api.FoodLocator;

import java.util.HashMap;
import java.util.Map;

public class FoodLocatorImpl implements FoodLocator
{
  private final JsonNode moveRequest;
  private final Map<Integer, Integer> foodLocations = new HashMap<>();

  public FoodLocatorImpl( final JsonNode moveRequest )
  {
    this.moveRequest = moveRequest;
  }

  @Override
  public Map<Integer, Integer> getAllAvailableFoodPositions()
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

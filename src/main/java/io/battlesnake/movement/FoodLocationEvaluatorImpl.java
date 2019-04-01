package io.battlesnake.movement;

import com.fasterxml.jackson.databind.JsonNode;
import io.battlesnake.api.FoodLocationEvaluator;

public class FoodLocationEvaluatorImpl implements FoodLocationEvaluator
{
  private final JsonNode moveRequest;

  public FoodLocationEvaluatorImpl( final JsonNode moveRequest )
  {
    this.moveRequest = moveRequest;
  }

  @Override
  public void findNearestFoodSource()
  {

  }
}

package BlackJack;

import BlackJack.model.Game;
import BlackJack.model.Subject;
import BlackJack.view.*;
import BlackJack.controller.*;

public class Program
{

  public static void main(String[] a_args)
  {
    //create subject object
    Game g = new Game(); //pass subject as param
    IView v = new SimpleView(); //new SwedishView();
    PlayGame ctrl = new PlayGame(v, g); //pass subject as param

    while (ctrl.Play());
  }
}
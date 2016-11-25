package BlackJack.controller;

import BlackJack.model.Subject;
import BlackJack.model.Subscriber;
import BlackJack.view.IView;
import BlackJack.model.Game;

public class PlayGame implements Subscriber {
  private IView a_view;
  private Game a_game;

  public PlayGame(IView a_view, Game a_game){
    this.a_view = a_view;
    this.a_game = a_game;

    this.a_game.Subscribe(this);
  }
  //implement observer and wait for furture updates
  public boolean Play() {
    a_view.DisplayWelcomeMessage();

    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());

    if (a_game.IsGameOver())
    {
        a_view.DisplayGameOver(a_game.IsDealerWinner());
    }

    int input = a_view.GetInput();
    
    if (input == 1)
    {
        a_game.NewGame();
    }
    else if (input == 2)
    {
        a_game.Hit();
    }
    else if (input == 3)
    {
        a_game.Stand(); //subject as parameter
    }

    return input != 4;
  }

  @Override
  public void Update() { //wait and then print
    System.out.println("wait");
      try {
        Thread.sleep(1800); //wait
      } catch (InterruptedException e) {
        for(int i = 0; i < 10000000; i++){} //fake timer..
      }


    a_view.DisplayDealerStatus();
    a_view.DisplayDealerHand(a_game.GetDealerHand(), a_game.GetDealerScore());
    a_view.DisplayPlayerHand(a_game.GetPlayerHand(), a_game.GetPlayerScore());
  }
}
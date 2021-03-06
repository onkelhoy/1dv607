package BlackJack.model.rules;

import BlackJack.model.Deck;
import BlackJack.model.Dealer;
import BlackJack.model.Player;

class AmericanNewGameStrategy implements INewGameStrategy {

  public boolean NewGame(Deck a_deck, Dealer a_dealer, Player a_player) {

    a_dealer.GetShowDeal(a_player, a_deck, true);
    a_dealer.GetShowDeal(a_dealer, a_deck, true);
    a_dealer.GetShowDeal(a_player, a_deck, true);
    a_dealer.GetShowDeal(a_dealer, a_deck, false);

    /*
    Card c;

    c = a_deck.GetCard();
    c.Show(true);
    a_player.DealCard(c);

    c = a_deck.GetCard();
    c.Show(true);
    a_dealer.DealCard(c);

    c = a_deck.GetCard();
    c.Show(true);
    a_player.DealCard(c);

    c = a_deck.GetCard();
    c.Show(false);
    a_dealer.DealCard(c);
    */
    return true;
  }
}
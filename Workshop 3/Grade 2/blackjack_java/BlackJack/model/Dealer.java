package BlackJack.model;

import BlackJack.model.rules.*;

import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player implements Publisher {
  //implement observer and update when getting a new card
  private List<Subscriber> subscribers = new ArrayList<>();
  private Deck m_deck;
  private INewGameStrategy m_newGameRule;
  private IHitStrategy m_hitRule;
  private IWinStrategy m_winRule;

  public Dealer(RulesFactory a_rulesFactory) {
  
    m_newGameRule = a_rulesFactory.GetNewGameRule();
    m_hitRule = a_rulesFactory.GetHitRule();
    m_winRule = a_rulesFactory.GetWinRule();
  }
  
  
  public boolean NewGame(Player a_player) {
    if (m_deck == null || IsGameOver()) {
      m_deck = new Deck();
      ClearHand();
      a_player.ClearHand();
      return m_newGameRule.NewGame(m_deck, this, a_player);   
    }
    return false;
  }

  public boolean Hit(Player a_player) {
    if (m_deck != null && a_player.CalcScore() < g_maxScore && !IsGameOver()) {

      GetShowDeal(a_player, m_deck, true);
      
      return true;
    }
    return false;
  }

  public boolean IsDealerWinner(Player a_player) {
    if (a_player.CalcScore() > g_maxScore) {
      return true;
    } else if (CalcScore() > g_maxScore) {
      return false;
    }
    return m_winRule.DealerWin(this, a_player);
  }

  public boolean IsGameOver() {
    if (m_deck != null && m_hitRule.DoHit(this) != true) {
        return true;
    }
    return false;
  }

  public boolean Stand(){
    if(m_deck != null){
      ShowHand();

      while(m_hitRule.DoHit(this)){
        Notify();
        GetShowDeal(this, m_deck, true);
      }

      Notify();
    }
    return true;
  }

  public void Notify(){
    for(Subscriber sub : subscribers){
      sub.Update();
    }
  }

  public void subscribe(Subscriber sub){
    subscribers.add(sub);
  }



  public void GetShowDeal(Player a_player, Deck a_deck, Boolean a_show){
    Card c = a_deck.GetCard();
    c.Show(a_show);
    a_player.DealCard(c);
  }
}
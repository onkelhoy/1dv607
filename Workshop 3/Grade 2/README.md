#Grade 2 requirements

###To-do list:
- [x] Download, compile and run the game in your development environment (remember that it should run, but it�s not playable).
- [x] Study the class diagram and the source code to understand the design of the game.
- [x] Implement the operation Game::Stand using the sequence diagram Game_Stand sequencediagram. The game should now be playable.
- [ ] Remove the bad, hidden, dependency between the controller and view (new game, hit, stand)
- [ ] Design and implement a new rule variant for when the dealer should take one more card. The new variant is �Soft 17?, use the same design pattern already present for Hit. Soft 17 means that the dealer has 17 but in a combination of Ace and 6 (for example Ace, two, two, two). This means that the Dealern can get another card valued at 10 but still have 17 as the value of the ace is reduced to 1. Using the soft 17 rule the dealer should take another card (compared to the original rule when the dealer only takes cards on a score of 16 or lower).
- [ ] Design and implement a variable rules for who wins the game. This variation could for example vary who wins on an equal score (in one implementation the Dealer wins, in the other the Player). The design should make it easy to add other variants without changing the Dealer.
- [x] The code for getting a card from the deck, show the card and give it to a player is duplicated in a number of places. Make a refactoring to remove this duplication and that supports low coupling/high cohesion. The code that is duplicated i similar to this:
	- Card c = deck.GetCard();
	- c.Show(true/false)
	- player.DealCard(c);
- [ ] Use the Observer-pattern to send an event to the user interface that a player has got a new card in his hand. When the event is handled the user interface should be redrawn to show the new hand (with the new card) and the game should be briefly paused, to make the game a bit more exciting, the pausing code should be in the user interface (view or controller) and not in the model. The pause should be when any player (dealer or player) gets a card.

###always do
Update the class diagram to reflect the changes you make (it is not necessary to recreate the whole diagram only the parts that have been affected by your changes).
You should have the following in your portflio and all parts should match

Source code for the entire application
An updated class diagram
import javafx.application.Application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.animation.PauseTransition;
import javafx.util.Duration;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.geometry.Pos;
import javafx.geometry.Insets;

import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;

import java.util.Random;

/*
 * Authors: 
 * 		- Ricardo Gonzalez (NetID: rgonza82)
 * 		- Quang Le		   (NetID: qle21)
 *
 * Project #3: Three Card Poker Game
 * CS 342: Software Design
 * Fall 2022
 * 
 */

public class ThreeCardPokerGame extends Application {

	private Player playerOne, playerTwo;
	private Dealer theDealer;

	private Button playBtn1, playBtn2, foldBtn1, foldBtn2, continueBtn;

	private VBox dealersVBox, gameInfoVBox, gameSceneRootVBox;
	private HBox playersHBox;

	private Label pPlus1Lbl, pPlus2Lbl, 
	anteBet1Lbl,anteBet2Lbl,
	playBet1Lbl, playBet2Lbl,
	dealerLbl, player1Lbl, player2Lbl, 
	totalWinnings1Lbl, totalWinnings2Lbl;

	private TextField pPlus1DisplayTxtFld, anteBet1DisplayTxtFld, playBet1DisplayTxtFld, 
	pPlus2DisplayTxtFld, anteBet2DisplayTxtFld, playBet2DisplayTxtFld,
	pPlus1PromptTxtFld, anteBet1PromptTxtFld, playBet1PromptTxtFld, 
	pPlus2PromptTxtFld, anteBet2PromptTxtFld, playBet2PromptTxtFld,
	totalWinnings1TxtFld, totalWinnings2TxtFld;

	private Label generalInfoLbl, player1FeedbackLbl, player2FeedbackLbl;

	private ImageView firstPlayerCard1, firstPlayerCard2, firstPlayerCard3,
	secondPlayerCard1, secondPlayerCard2, secondPlayerCard3,
	dealerCard1, dealerCard2, dealerCard3;

	private MenuItem exitMItem, freshStartMItem, newLookMItem;

	//PauseTransition resultsPause = new PauseTransition(Duration.seconds(8));
	private PauseTransition dealingCardsPause = new PauseTransition(Duration.seconds(2));
	private PauseTransition initialPause = new PauseTransition(Duration.seconds(3));
	private PauseTransition exitPause = new PauseTransition(Duration.seconds(3));

	private static final int CARD_WIDTH = 100;
	private static final int CARD_HEIGHT= 145;

	private static final String HANDS[] = {"High Card", "Straight Flush", "Three of a Kind", "Straight", "Flush", "Pair"};
	private static final String SUITS[] = {"Clubs", "Diamonds", "Spades", "Hearts"};
	private static final String VALUES[] = {null, null, "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack","Queen","King","Ace"};

	private static final String COLORS[] = {"#004417", "#0d1a4b", "#580000", "#043538", "#341c02"};
	//private static final Image BACKGROUNDS[] = {new Image("background1.png"),new Image("background2.png"),
	//											new Image("background3.png"),new Image("background4.png")};

	private Image cardBackImage = new Image("cardBack.png");

	private String currentBackgroundColor = COLORS[0];
	private Image 	currentCardBackground = new Image("background1.png"); //  BACKGROUNDS[0];

	private boolean otherPlayerAlreadyPlayed, player1Folded, player2Folded = false;
	private boolean anteBet1Made,anteBet2Made, playBet1Made, playBet2Made, 
					dealerPlayedHand, cardsWereDealed = false;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Three Card Poker Game");

		initializeAllFields();

		MenuBar menuBar = createMenuBar();
		dealersVBox = createDealersBox();
		gameInfoVBox = createGameInfoBox();
		playersHBox = createPlayersBox();

		gameSceneRootVBox = new VBox(menuBar, dealersVBox, gameInfoVBox, playersHBox);
		currentBackgroundColor = COLORS[0];
		gameSceneRootVBox.setStyle("-fx-background-color: " + currentBackgroundColor);

		Scene gameScene = new Scene(gameSceneRootVBox, 1200,800);


		Button playAgain = new Button("Play Again");
		playAgain.setPrefSize(200, 100);
		playAgain.setStyle( "-fx-font-family: 'Arial Black'; -fx-font-size: 17;");
		playAgain.setOnAction(e-> { 
			primaryStage.setScene(gameScene);
			reset();
		});


		VBox exitRoot = new VBox(playAgain);
		exitRoot.setStyle("-fx-background-color: #08571a");
		exitRoot.setAlignment(Pos.CENTER);
		Scene exitScene = new Scene(exitRoot,1200,800);

		newLookMItem.setOnAction(e-> { 
			
			Image BACKGROUNDS[] = {new Image("background1.png"), new Image("background2.png"),new Image("background3.png"),new Image("background4.png")};
			
			Random indxGenerator = new Random();
			String nextColor = COLORS[indxGenerator.nextInt(COLORS.length)];

			while(nextColor == currentBackgroundColor) {
				nextColor = COLORS[indxGenerator.nextInt(COLORS.length)];
			}
			currentBackgroundColor = nextColor;
			gameSceneRootVBox.setStyle("-fx-background-color: " + currentBackgroundColor);
			exitRoot.setStyle("-fx-background-color: " + currentBackgroundColor);

			Image nextCardBackground = BACKGROUNDS[indxGenerator.nextInt(BACKGROUNDS.length)];

			while(nextCardBackground.getUrl() == currentCardBackground.getUrl()) {
				nextCardBackground = BACKGROUNDS[indxGenerator.nextInt(BACKGROUNDS.length)];
			}
			currentCardBackground = nextCardBackground;		

			if (!cardsWereDealed) {
				firstPlayerCard1.setImage(currentCardBackground);
				firstPlayerCard2.setImage(currentCardBackground);
				firstPlayerCard3.setImage(currentCardBackground);
				secondPlayerCard1.setImage(currentCardBackground);
				secondPlayerCard2.setImage(currentCardBackground);
				secondPlayerCard3.setImage(currentCardBackground);
				dealerCard1.setImage(currentCardBackground);
				dealerCard2.setImage(currentCardBackground);
				dealerCard3.setImage(currentCardBackground);
			}
		});

		primaryStage.setScene(gameScene);
		primaryStage.show();

		initializeAllPlayers();

		initialPause.setOnFinished(e -> {
			playersHBox.setDisable(false);
			dealersVBox.setDisable(false);
			generalInfoLbl.setText("Make all your bets!");
		});

		initialPause.play();

		exitPause.setOnFinished(e -> {

			primaryStage.setScene(exitScene);

		});

		exitMItem.setOnAction(e-> {
			generalInfoLbl.setText("Thanks for playing! Exiting...");
			dealersVBox.setDisable(true);
			playersHBox.setDisable(true);
			exitPause.play();
		});

		dealingCardsPause.setOnFinished(e-> {  // Wait for three seconds, then do this.
			dealHands();
			cardsWereDealed = true;
			generalInfoLbl.setText("Make your play bets");
		});

		continueBtn.setOnAction(e-> {
			continueBtn.setDisable(true);
			cardsWereDealed = false;

			if (dealerPlayedHand || (player1Folded && player2Folded)) {  // let's say dealer did not play hand. We have to check as well if both players folded.
				cleanPlayerPrompts(1);
				cleanPlayerPrompts(2);
				cleanCardFields();

				generalInfoLbl.setText("Make your bets!");
				player1FeedbackLbl.setText("");
				player2FeedbackLbl.setText("");
				playBet1PromptTxtFld.setDisable(true);
				playBet2PromptTxtFld.setDisable(true);
				//restorePlayerBets(playerOne);
				//restorePlayerBets(playerTwo);
				restorePlayerBetFlags(1);
				restorePlayerBetFlags(2);
			} else {
				dealerDidNotPlayLogic();
			}
		});

		freshStartMItem.setOnAction(e-> {
			reset();
		});

		playButtonEventLogic(playerOne, playBtn1, foldBtn1, playBet1DisplayTxtFld,1);
		playButtonEventLogic(playerTwo, playBtn2, foldBtn2, playBet2DisplayTxtFld,2);

		foldButtonEventLogic(foldBtn1, playBtn1, 1);
		foldButtonEventLogic(foldBtn2, playBtn2, 2);

		anteBetPromptEventLogic(playerOne,anteBet1PromptTxtFld,anteBet1DisplayTxtFld,pPlus1PromptTxtFld, player1FeedbackLbl, 1);
		anteBetPromptEventLogic(playerTwo,anteBet2PromptTxtFld,anteBet2DisplayTxtFld,pPlus2PromptTxtFld, player2FeedbackLbl, 2);

		pPlusPromptEventLogic(playerOne, pPlus1PromptTxtFld, pPlus1DisplayTxtFld, player1FeedbackLbl, 1);
		pPlusPromptEventLogic(playerTwo, pPlus2PromptTxtFld, pPlus2DisplayTxtFld, player2FeedbackLbl, 2);

		playBetPromptEventLogic(playerOne, playBet1PromptTxtFld, playBet1DisplayTxtFld,pPlus1PromptTxtFld, player1FeedbackLbl, 1);
		playBetPromptEventLogic(playerTwo, playBet2PromptTxtFld, playBet2DisplayTxtFld,pPlus2PromptTxtFld, player2FeedbackLbl, 2);
	}

	private void pPlusPromptEventLogic(Player player, 
			TextField pPlusPromptTxtFld,
			TextField pPlusDisplayTxtFld,
			Label playerFeedbackLbl,
			int whichPlayer) {

		pPlusPromptTxtFld.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)){

			String str = pPlusPromptTxtFld.getText();
			int pairPlusBet = Integer.parseInt(str);

			if (pairPlusBet < 5 || pairPlusBet > 25) {


				playerFeedbackLbl.setText("Pair Plus bet must be between\n        $5 and $25");

			} else  {
				playerFeedbackLbl.setText("");
				player.setPairPlusBet(pairPlusBet);
				pPlusDisplayTxtFld.setText(str);
				pPlusPromptTxtFld.clear();
				/*
				if (whichPlayer == 1) {
					pPlusBet1Made = true;
				}

				if (whichPlayer == 2) {
					pPlusBet2Made = true;
				}
				 */
				pPlusPromptTxtFld.setText("Pair Plus Bet Made");
				pPlusPromptTxtFld.setDisable(true);
			}

		}
		});
	}

	private void anteBetPromptEventLogic(Player player, 
			TextField anteBetPromptTxtFld,
			TextField anteBetDisplayTxtFld,
			TextField pPlusPromptTxtFld,
			Label playerFeedbackLbl,
			int whichPlayer) {

		anteBetPromptTxtFld.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)){

			String str = anteBetPromptTxtFld.getText();
			int anteBet = Integer.parseInt(str);

			if (anteBet < 5 || anteBet > 25) {
				playerFeedbackLbl.setText("Ante bet must be between\n        $5 and $25");
			} else {
				playerFeedbackLbl.setText("");
				player.setAnteBet(anteBet);
				anteBetDisplayTxtFld.setText(str);
				anteBetPromptTxtFld.clear();

				if (whichPlayer == 1) {
					anteBet1Made = true;
				}

				if (whichPlayer == 2) {
					anteBet2Made = true;
				}	

				anteBetPromptTxtFld.setText("Ante Bet Made");
				anteBetPromptTxtFld.setDisable(true);

				if (allAnteBetsMade()) {
					player1FeedbackLbl.setText("");
					player2FeedbackLbl.setText("");
					pPlus1PromptTxtFld.setDisable(true);
					pPlus2PromptTxtFld.setDisable(true);

					playBet1PromptTxtFld.setDisable(false);
					playBet2PromptTxtFld.setDisable(false);
					generalInfoLbl.setText("Dealing Cards...");
					dealingCardsPause.play();
				}
			}	
		}
		});
	}

	private void playBetPromptEventLogic(Player player, 
			TextField playBetPromptTxtFld,
			TextField playBetDisplayTxtFld,
			TextField pPlusPromptTxtFld,
			Label playerFeedbackLbl,
			int whichPlayer) {

		playBetPromptTxtFld.setOnKeyPressed(e -> {if(e.getCode().equals(KeyCode.ENTER)){

			String str = playBetPromptTxtFld.getText();
			int playBet = Integer.parseInt(str);

			if (playBet != player.getAnteBet()) {

				playerFeedbackLbl.setText("Play bet must be equal\n      to the ante bet");
			} else {
				playerFeedbackLbl.setText("");
				player.setAnteBet(playBet);
				playBetDisplayTxtFld.setText(str);
				playBetPromptTxtFld.clear();

				if (whichPlayer == 1) {
					playBet1Made = true;

				}

				if (whichPlayer == 2) {
					playBet2Made = true;
				}	

				playBetPromptTxtFld.setText("Play Bet Made");
				generalInfoLbl.setText("Make your play bets");
				playBetPromptTxtFld.setDisable(true);

				if (allPlayBetsMade()) {
					player1FeedbackLbl.setText("");
					player2FeedbackLbl.setText("");
					enableButtons();
					generalInfoLbl.setText("Click Play or Fold");
				}
			}
		}
		});
	}

	private void playButtonEventLogic(Player player, 
			Button playBtn, 
			Button foldBtn, 
			TextField playBetDisplayTxtFld,
			int whichPlayer) {

		playBtn.setOnAction(e-> {

			player.setPlayBet(player.getAnteBet());
			playBetDisplayTxtFld.setText(player.getPlayBet()+"");

			playBtn.setDisable(true);
			foldBtn.setDisable(true);

			if (otherPlayerAlreadyPlayed) {
				revealDealersHand();
				displayResults();

			}
			otherPlayerAlreadyPlayed = !otherPlayerAlreadyPlayed;
		});
	}

	private void foldButtonEventLogic(Button foldBtn, Button playBtn, int whichPlayer) {

		foldBtn.setOnAction(e-> {
			playBtn.setDisable(true);
			foldBtn.setDisable(true);

			if (whichPlayer == 1) {
				player1Folded = true;
			}

			if (whichPlayer == 2) {
				player2Folded = true;
			}

			if (otherPlayerAlreadyPlayed) {
				revealDealersHand();
				displayResults();
			}

			otherPlayerAlreadyPlayed = !otherPlayerAlreadyPlayed;
		});
	}

	private String calculateResults(Player player, Dealer dealer, String whichPlayer, boolean folded) {
		int plyrHandEval = ThreeCardLogic.evalHand(player.getHand());
		int dealerHandEval = ThreeCardLogic.evalHand(dealer.getDealersHand());

		int plyrWinnings = 0;
		int plyrPairPlusWinnings = ThreeCardLogic.evalPPWinnings(player.getHand(), player.getPairPlusBet());

		int plyrVsDealerWinnerNum = ThreeCardLogic.compareHands(dealer.getDealersHand(), player.getHand());

		String playerAllInfo = "";
		String winnerInfo = "";

		// For Tie or if dealer can't play
		int dealersHigh = dealer.getDealersHand().get(dealer.getDealersHand().size() - 1).getValue();

		if (!folded) {
			
			if (plyrVsDealerWinnerNum == 0) {
				
				if (dealersHigh < 12) {

					if (folded) {
						plyrWinnings = 0 - player.getAnteBet() - player.getPairPlusBet();
						player.setTotalWinnings(player.getTotalWinnings() + plyrWinnings + plyrPairPlusWinnings);
					}
					return ""; // Dealer can't play the hand
				}
				
				winnerInfo = "Tie.";
			} else if (plyrVsDealerWinnerNum == 1) {
				winnerInfo = "DEALER WINS.";
				
				plyrWinnings = 0 - player.getAnteBet() - player.getPlayBet() - player.getPairPlusBet();
			} else if (plyrVsDealerWinnerNum == 2) {
				winnerInfo = "PLAYER " + whichPlayer +  " WINS.";
				plyrWinnings = player.getAnteBet() + player.getPlayBet();
			}

			playerAllInfo = "     Player " + whichPlayer + "'s " + getHandType(plyrHandEval) + 
					"\n		vs\n   Dealer's " + getHandType(dealerHandEval) + ".\n       " + winnerInfo;
		} else {
			winnerInfo = "Player " + whichPlayer + " folded.";
			playerAllInfo = winnerInfo;
			plyrWinnings = 0 - player.getAnteBet() - player.getPairPlusBet();
		}

		player.setTotalWinnings(player.getTotalWinnings() + plyrWinnings + plyrPairPlusWinnings);
		return playerAllInfo;
	}

	private void restorePlayerBets(Player player) {
		player.setPairPlusBet(0);
		player.setAnteBet(0);
		player.setPlayBet(0);
		player.setTotalWinnings(0);
	}

	private void dealerDidNotPlayLogic() {

		cleanCardFields();

		generalInfoLbl.setText("");

		player1FeedbackLbl.setText("");
		player2FeedbackLbl.setText("");

		if (!player1Folded && !player2Folded) {	

			playBet2PromptTxtFld.clear();
			playBet2DisplayTxtFld.clear();
			playBet1PromptTxtFld.setDisable(true);
			playBet2PromptTxtFld.setDisable(true);

			pPlus1PromptTxtFld.clear();
			pPlus1DisplayTxtFld.clear();
			pPlus1PromptTxtFld.setDisable(true);
			pPlus2PromptTxtFld.setDisable(true);

			generalInfoLbl.setText("Both player's ante bet were pushed, \n               Dealing Cards...");
			dealingCardsPause.play();

			playBet1PromptTxtFld.setDisable(false);
			playBet2PromptTxtFld.setDisable(false);

			pPlus1PromptTxtFld.setPromptText("Enter Pair Plus Bet");
			pPlus1PromptTxtFld.setDisable(false);

			pPlus2PromptTxtFld.clear();
			pPlus2DisplayTxtFld.clear();
			pPlus2PromptTxtFld.setPromptText("Enter Pair Plus Bet");
			pPlus2PromptTxtFld.setDisable(false);

			playBet1PromptTxtFld.clear();
			playBet1DisplayTxtFld.clear();
			playBet1PromptTxtFld.setPromptText("Enter Play Bet");
			//playBet1PromptTxtFld.setDisable(false);

			playBet2PromptTxtFld.setPromptText("Enter Play Bet");
			//playBet2PromptTxtFld.setDisable(false);		        				

			playBet1Made = false;
			playBet2Made = false;


		} else {

			// player folded->loses money, has to bet all over again
			if (player1Folded) {

				generalInfoLbl.setText("Player 1 folded. He must make\n      an ante bet again.");

				cleanPlayerPrompts(1);
				//restorePlayerBets(playerOne);
				restorePlayerBetFlags(1);

			} else if (!player1Folded) { // player 1 played

				player1FeedbackLbl.setText("Wait for player 2 to make ante bet");

				pPlus1PromptTxtFld.clear();
				pPlus1DisplayTxtFld.clear();
				pPlus1PromptTxtFld.setPromptText("Enter Pair Plus Bet");
				pPlus1PromptTxtFld.setDisable(false);

				playBet1PromptTxtFld.clear();
				playBet1DisplayTxtFld.clear();
				playBet1PromptTxtFld.setPromptText("Enter Play Bet");
				playBet1PromptTxtFld.setDisable(true);

				playerOne.setPairPlusBet(0);
				playerOne.setPlayBet(0);
				playBet1Made = false;
			}

			if (player2Folded) {

				generalInfoLbl.setText("Player 2 folded. He must make\n      an ante bet again.");

				cleanPlayerPrompts(2);
				//restorePlayerBets(playerTwo);
				restorePlayerBetFlags(2);

			} else if (!player2Folded) {  // player 2 played

				player2FeedbackLbl.setText("Wait for player 1 to make ante bet");

				pPlus2PromptTxtFld.clear();
				pPlus2DisplayTxtFld.clear();
				pPlus2PromptTxtFld.setPromptText("Enter Pair Plus Bet");
				pPlus2PromptTxtFld.setDisable(true);

				playBet2PromptTxtFld.clear();
				playBet2DisplayTxtFld.clear();
				playBet2PromptTxtFld.setPromptText("Enter Play Bet");

				playerTwo.setPairPlusBet(0);
				playerTwo.setPlayBet(0);
				playBet2Made = false;
			}
		}
	}

	private void displayResults() {

		String player1Results = calculateResults(playerOne, theDealer, "1", player1Folded);
		String player2Results = calculateResults(playerTwo, theDealer, "2", player2Folded);

		totalWinnings1TxtFld.setText(playerOne.getTotalWinnings() + "");
		totalWinnings2TxtFld.setText(playerTwo.getTotalWinnings() + "");

		player1FeedbackLbl.setText(player1Results);
		player2FeedbackLbl.setText(player2Results);

		if (player1Results == "") { // Dealer cannot play

			if (player1Folded && player2Folded ) {
				player1FeedbackLbl.setText("Player 1 folded");
				player2FeedbackLbl.setText("Player 2 folded");
				generalInfoLbl.setText("");
			} else {
				generalInfoLbl.setText("Dealer doesn't have at least a Queen,\n he can't play the hand. Click Continue");

				if (player1Folded) {
					player1FeedbackLbl.setText("Player 1 folded");

				} else if (!player1Folded) {

					player1FeedbackLbl.setText("Ante Bet pushed to the next hand");
				}
				if (player2Folded) {
					player2FeedbackLbl.setText("Player 2 folded");

				} else if (!player2Folded) {
					player2FeedbackLbl.setText("Ante Bet pushed to the next hand");
				}
				dealerPlayedHand = false;
			}
		} else {

			dealerPlayedHand = true;
			generalInfoLbl.setText("Click 'continue'");
		}
		continueBtn.setDisable(false);	
	}


	private void initializeAllPlayers() {
		theDealer = new Dealer();
		playerOne = new Player();
		playerTwo = new Player();
	}

	private void reset() {
		generalInfoLbl.setText("WELCOME TO THE THREE\n    CARD POKER GAME!");
		cleanPlayerPrompts(1);
		cleanPlayerPrompts(2);
		cleanCardFields();

		//initializeAllPlayers();
	
		restorePlayerBetFlags(1);
		restorePlayerBetFlags(2);

		cardsWereDealed = false;

		restorePlayerBets(playerOne);
		restorePlayerBets(playerTwo);

		initialPause.play();

		totalWinnings1TxtFld.setText("0");
		totalWinnings2TxtFld.setText("0");
		player1FeedbackLbl.setText("");
		player2FeedbackLbl.setText("");
	}

	private void cleanCardFields() {
		firstPlayerCard1.setImage(currentCardBackground);
		firstPlayerCard2.setImage(currentCardBackground);
		firstPlayerCard3.setImage(currentCardBackground);

		secondPlayerCard1.setImage(currentCardBackground);
		secondPlayerCard2.setImage(currentCardBackground);
		secondPlayerCard3.setImage(currentCardBackground);

		dealerCard1.setImage(currentCardBackground);
		dealerCard2.setImage(currentCardBackground);
		dealerCard3.setImage(currentCardBackground);
	}

	private void cleanPlayerPrompts(int whichPlayer) {
		if (whichPlayer == 1) {
			pPlus1DisplayTxtFld.setText("");
			pPlus1PromptTxtFld.setPromptText("Enter Pair Plus Bet");
			pPlus1PromptTxtFld.clear();
			pPlus1PromptTxtFld.setDisable(false);

			anteBet1DisplayTxtFld.setText("");
			anteBet1PromptTxtFld.setPromptText("Enter Ante Bet");
			anteBet1PromptTxtFld.setDisable(false);
			anteBet1PromptTxtFld.clear();

			playBet1DisplayTxtFld.setText("");
			playBet1PromptTxtFld.setPromptText("Enter Play Bet");
			playBet1PromptTxtFld.setDisable(true);
			playBet1PromptTxtFld.clear();
		}

		if (whichPlayer == 2) {
			pPlus2DisplayTxtFld.setText("");
			pPlus2PromptTxtFld.setPromptText("Enter Pair Plus Bet");
			pPlus2PromptTxtFld.clear();
			pPlus2PromptTxtFld.setDisable(false);

			anteBet2DisplayTxtFld.setText("");
			anteBet2PromptTxtFld.setPromptText("Enter Ante Bet");
			anteBet2PromptTxtFld.clear();
			anteBet2PromptTxtFld.setDisable(false);

			playBet2DisplayTxtFld.setText("");
			playBet2PromptTxtFld.setPromptText("Enter Play Bet");
			playBet2PromptTxtFld.clear();
			playBet2PromptTxtFld.setDisable(true);
		}	
	}

	private void restorePlayerBetFlags(int whichPlayer) {

		if (whichPlayer == 1) {
			//pPlusBet1Made = false;
			anteBet1Made= false;
			playBet1Made= false; 
			player1Folded = false;
		}

		if (whichPlayer == 2) {
			//pPlusBet2Made = false;
			anteBet2Made= false; 
			playBet2Made = false;
			player2Folded = false;
		}
	}

	private void enableButtons() {
		playBtn1.setDisable(false);
		playBtn2.setDisable(false);
		foldBtn1.setDisable(false);
		foldBtn2.setDisable(false);
	}

	private boolean allAnteBetsMade() {
		return anteBet1Made && anteBet2Made/* && playBet1Made && playBet2Made*/;
	}

	private boolean allPlayBetsMade() {
		return playBet1Made && playBet2Made;
	}

	private String getHandType(int eval) {
		return HANDS[eval];
	}

	private String getValueString(int value) {
		return VALUES[value];
	}

	private String getSuitString(char suit) {

		if (suit == 'C') {
			return SUITS[0];
		} else if (suit == 'D') {
			return SUITS[1];
		} else if (suit == 'S') {
			return SUITS[2];
		} else if (suit == 'H') {
			return SUITS[3];
		} else {
			return "";
		}
	}

	private void revealDealersHand() {
		fillDealerCardFields();
	}


	private void fillPlayerCardFields(Player player, ImageView card1, ImageView card2, ImageView card3) {

		String nextSuit = getSuitString(player.getHand().get(0).getSuit()).toLowerCase();
		//System.out.println(nextSuit);
		String nextValue = getValueString(player.getHand().get(0).getValue()).toLowerCase();
		String nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard);
		Image plyr1Card1 = new Image(nextCard);

		card1.setImage(plyr1Card1);

		nextSuit = getSuitString(player.getHand().get(1).getSuit()).toLowerCase();
		//System.out.println(nextSuit);
		nextValue = getValueString(player.getHand().get(1).getValue()).toLowerCase();
		nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard);
		Image plyr1Card2 = new Image(nextCard);

		card2.setImage(plyr1Card2);

		nextSuit = getSuitString(player.getHand().get(2).getSuit()).toLowerCase();
		//System.out.println(nextSuit);
		nextValue = getValueString(player.getHand().get(2).getValue()).toLowerCase();
		nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard + "\n");
		Image plyr1Card3 = new Image(nextCard);

		card3.setImage(plyr1Card3);
	}

	private void fillDealerCardFields() {
		//System.out.println("DEALERS CARDS");
		String nextSuit = getSuitString(theDealer.getDealersHand().get(0).getSuit()).toLowerCase();
		String nextValue = getValueString(theDealer.getDealersHand().get(0).getValue()).toLowerCase();
		String nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard);
		Image plyr1Card1 = new Image(nextCard);
		dealerCard1.setImage(plyr1Card1);

		nextSuit = getSuitString(theDealer.getDealersHand().get(1).getSuit()).toLowerCase();
		nextValue = getValueString(theDealer.getDealersHand().get(1).getValue()).toLowerCase();
		nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard);
		Image plyr1Card2 = new Image(nextCard);
		dealerCard2.setImage(plyr1Card2);

		nextSuit = getSuitString(theDealer.getDealersHand().get(2).getSuit()).toLowerCase();
		nextValue = getValueString(theDealer.getDealersHand().get(2).getValue()).toLowerCase();
		nextCard = nextValue + "_of_" + nextSuit + ".png";
		//System.out.println(nextCard + "\n");
		Image plyr1Card3 = new Image(nextCard);
		dealerCard3.setImage(plyr1Card3);
	}

	private void dealHands() {
		playerOne.setHand(theDealer.dealHand());
		playerTwo.setHand(theDealer.dealHand());
		theDealer.setDealersHand(theDealer.dealHand());
		/*

		Card card1 = new Card('H',11);
		Card card2 = new Card('C',4);
		Card card3 = new Card('H',3);
		ArrayList<Card> testHand = new ArrayList<Card>();
		testHand.add(card1);
		testHand.add(card2);
		testHand.add(card3);
		theDealer.setDealersHand(testHand);
		 */

		fillPlayerCardFields(playerOne, firstPlayerCard1, firstPlayerCard2, firstPlayerCard3);
		fillPlayerCardFields(playerTwo, secondPlayerCard1, secondPlayerCard2, secondPlayerCard3);

		dealerCard1.setImage(cardBackImage);
		dealerCard2.setImage(cardBackImage);
		dealerCard3.setImage(cardBackImage);

	}

	private void initTextFields() {

		pPlus1DisplayTxtFld = new TextField("");
		pPlus2DisplayTxtFld  = new TextField("");
		pPlus1PromptTxtFld  = new TextField();
		pPlus2PromptTxtFld  = new TextField();
		pPlus1DisplayTxtFld.setEditable(false);
		pPlus2DisplayTxtFld.setEditable(false);
		pPlus1PromptTxtFld.setPromptText("Enter Pair Plus Bet");
		pPlus2PromptTxtFld.setPromptText("Enter Pair Plus Bet");

		pPlus1PromptTxtFld.setAlignment(Pos.CENTER);
		pPlus2PromptTxtFld.setAlignment(Pos.CENTER);
		pPlus1DisplayTxtFld.setPrefSize(50,20);
		pPlus2DisplayTxtFld.setPrefSize(50,20);

		anteBet1DisplayTxtFld = new TextField("");
		anteBet2DisplayTxtFld = new TextField("");
		anteBet1PromptTxtFld = new TextField();
		anteBet2PromptTxtFld = new TextField(); 
		anteBet1DisplayTxtFld.setEditable(false);
		anteBet2DisplayTxtFld.setEditable(false);
		anteBet1PromptTxtFld.setPromptText("Enter Ante Bet");
		anteBet2PromptTxtFld.setPromptText("Enter Ante Bet");

		anteBet1PromptTxtFld.setAlignment(Pos.CENTER);
		anteBet2PromptTxtFld.setAlignment(Pos.CENTER);
		anteBet1DisplayTxtFld.setPrefSize(50,20);
		anteBet2DisplayTxtFld.setPrefSize(50,20);


		playBet1DisplayTxtFld = new TextField(""); 
		playBet2DisplayTxtFld = new TextField("");
		playBet1PromptTxtFld = new TextField();
		playBet2PromptTxtFld = new TextField();
		playBet1DisplayTxtFld.setEditable(false);
		playBet2DisplayTxtFld.setEditable(false);
		playBet1PromptTxtFld.setPromptText("Enter Play Bet");
		playBet2PromptTxtFld.setPromptText("Enter Play Bet");
		playBet1PromptTxtFld.setDisable(true);
		playBet2PromptTxtFld.setDisable(true);
		//playBet1DisplayTxtFld.setDisable(true);
		//playBet2DisplayTxtFld.setDisable(true);



		playBet1PromptTxtFld.setAlignment(Pos.CENTER);
		playBet2PromptTxtFld.setAlignment(Pos.CENTER);
		playBet1DisplayTxtFld.setPrefSize(50,20);
		playBet2DisplayTxtFld.setPrefSize(50,20);

		totalWinnings1TxtFld = new TextField("0");
		totalWinnings2TxtFld = new TextField("0");
		totalWinnings1TxtFld.setPrefSize(50,20);
		totalWinnings2TxtFld.setPrefSize(50,20);
		totalWinnings1TxtFld.setEditable(false);
		totalWinnings2TxtFld.setEditable(false);
	}

	private void initImages() {

		firstPlayerCard1= new ImageView(currentCardBackground);
		firstPlayerCard1.setFitHeight(CARD_HEIGHT);
		firstPlayerCard1.setFitWidth(CARD_WIDTH);

		firstPlayerCard2= new ImageView(currentCardBackground);
		firstPlayerCard2.setFitHeight(CARD_HEIGHT);
		firstPlayerCard2.setFitWidth(CARD_WIDTH);

		firstPlayerCard3 = new ImageView(currentCardBackground);
		firstPlayerCard3.setFitHeight(CARD_HEIGHT);
		firstPlayerCard3.setFitWidth(CARD_WIDTH);

		secondPlayerCard1= new ImageView(currentCardBackground);
		secondPlayerCard1.setFitHeight(CARD_HEIGHT);
		secondPlayerCard1.setFitWidth(CARD_WIDTH);

		secondPlayerCard2= new ImageView(currentCardBackground);
		secondPlayerCard2.setFitHeight(CARD_HEIGHT);
		secondPlayerCard2.setFitWidth(CARD_WIDTH);

		secondPlayerCard3 = new ImageView(currentCardBackground);
		secondPlayerCard3.setFitHeight(CARD_HEIGHT);
		secondPlayerCard3.setFitWidth(CARD_WIDTH);

		dealerCard1= new ImageView(currentCardBackground);
		dealerCard1.setFitHeight(CARD_HEIGHT);
		dealerCard1.setFitWidth(CARD_WIDTH);

		dealerCard2= new ImageView(currentCardBackground);
		dealerCard2.setFitHeight(CARD_HEIGHT);
		dealerCard2.setFitWidth(CARD_WIDTH);

		dealerCard3 = new ImageView(currentCardBackground);
		dealerCard3.setFitHeight(CARD_HEIGHT);
		dealerCard3.setFitWidth(CARD_WIDTH);
	}

	private void initButtonFields() {

		playBtn1 = new Button("Play");
		playBtn2 = new Button("Play");
		foldBtn1 = new Button("Fold");
		foldBtn2 = new Button("Fold");
		continueBtn = new Button("Continue");

		playBtn1.setDisable(true);
		playBtn2.setDisable(true);
		foldBtn1.setDisable(true);
		foldBtn2.setDisable(true);
		continueBtn.setDisable(true);


		playBtn1.setPrefSize(100,50);
		playBtn2.setPrefSize(100,50);
		foldBtn1.setPrefSize(100,50);
		foldBtn2.setPrefSize(100,50);
	}

	private void initLabelFields() {

		player1Lbl = new Label("Player 1's Hand");
		player2Lbl = new Label("Player 2's Hand");

		player1Lbl.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 20;");
		player1Lbl.setTextFill(Color.WHITE);
		player2Lbl.setStyle("-fx-font-family:'Arial Black'; -fx-font-size: 20;");
		player2Lbl.setTextFill(Color.WHITE);

		dealerLbl =  new Label("Dealer's Hand");
		dealerLbl.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 20;");
		dealerLbl.setTextFill(Color.WHITE);

		// Pair Plus Bet
		pPlus1Lbl = new Label("Pair Plus Bet");
		pPlus2Lbl = new Label("Pair Plus Bet");
		pPlus1Lbl.setTextFill(Color.WHITE);
		pPlus2Lbl.setTextFill(Color.WHITE);

		// Ante Bet
		anteBet1Lbl = new Label("Ante Bet");
		anteBet2Lbl = new Label("Ante Bet");
		anteBet1Lbl.setTextFill(Color.WHITE);
		anteBet2Lbl.setTextFill(Color.WHITE);

		// Play Bet
		playBet1Lbl = new Label("Play Bet");
		playBet2Lbl = new Label("Play Bet");
		playBet1Lbl.setTextFill(Color.WHITE);
		playBet2Lbl.setTextFill(Color.WHITE);
		//playBet1Lbl.setDisable(true);
		//playBet2Lbl.setDisable(true);


		totalWinnings1Lbl = new Label("   Total \nWinnings:");
		totalWinnings2Lbl = new Label("   Total \nWinnings:");
		totalWinnings1Lbl.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14;");
		totalWinnings2Lbl.setStyle("-fx-font-family: 'Arial Black'; -fx-font-size: 14;");
		totalWinnings1Lbl.setTextFill(Color.WHITE);
		totalWinnings2Lbl.setTextFill(Color.WHITE);	
	}

	private void initMenuItems() {
		exitMItem = new MenuItem("Exit");
		freshStartMItem = new MenuItem("Fresh Start");
		newLookMItem = new MenuItem("New Look");
	}

	private void initializeAllFields() {
		initMenuItems();
		initTextFields();
		initLabelFields();
		initButtonFields();
		initImages();
	}

	private MenuBar createMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Options");
		menu.getItems().add(freshStartMItem);
		menu.getItems().add(newLookMItem);
		menu.getItems().add(exitMItem);
		menuBar.getMenus().add(menu);

		return menuBar;
	}

	private VBox createDealersBox() {
		HBox dealersLblBox = new HBox(dealerLbl);
		HBox dealersCardsBox = new HBox(dealerCard1, dealerCard2, dealerCard3);

		dealersLblBox.setAlignment(Pos.CENTER);
		dealersCardsBox.setAlignment(Pos.CENTER);
		dealersCardsBox.setSpacing(10);

		VBox dealersVBox = new VBox(dealersLblBox,dealersCardsBox);
		dealersVBox.setSpacing(20);
		VBox.setMargin(dealersVBox, new Insets(20,40,20,40));
		return dealersVBox;
	}

	private VBox createGameInfoBox() {

		String style = "-fx-font-family: 'Arial Black'; -fx-font-size: 17; -fx-border-color: black; -fx-border-width: 3;";
		String stylePlayers = "-fx-font-family: 'Arial Black'; -fx-font-size: 12; -fx-border-color: black; -fx-border-width: 3;";
		generalInfoLbl = new Label("WELCOME TO THE THREE\n     CARD POKER GAME!");
		player1FeedbackLbl = new Label("");
		player2FeedbackLbl = new Label("");

		generalInfoLbl.setStyle(style);
		generalInfoLbl.setPrefSize(450, 100);
		generalInfoLbl.setAlignment(Pos.CENTER);
		generalInfoLbl.setTextFill(Color.WHITE);

		player1FeedbackLbl.setStyle(stylePlayers); 
		player1FeedbackLbl.setPrefSize(300, 200);
		player1FeedbackLbl.setAlignment(Pos.CENTER);
		player1FeedbackLbl.setTextFill(Color.WHITE);

		player2FeedbackLbl.setStyle(stylePlayers);
		player2FeedbackLbl.setPrefSize(300, 200);
		player2FeedbackLbl.setAlignment(Pos.CENTER);
		player2FeedbackLbl.setTextFill(Color.WHITE);



		HBox infoHBox = new HBox(player1FeedbackLbl, generalInfoLbl, player2FeedbackLbl);
		infoHBox.setAlignment(Pos.CENTER);
		infoHBox.setSpacing(30);
		HBox.setMargin(infoHBox, new Insets(0,0,20,0));


		VBox gameInfoBox = new VBox(infoHBox, continueBtn);
		gameInfoBox.setAlignment(Pos.CENTER);
		VBox.setMargin(generalInfoLbl, new Insets(0,300,20,300));
		VBox.setMargin(continueBtn, new Insets(20,0,20,0));

		return gameInfoBox;
	}

	private HBox createPlayersBox() {

		HBox pPlus1HBox = new HBox(pPlus1Lbl, pPlus1DisplayTxtFld, pPlus1PromptTxtFld);
		HBox pPlus2HBox = new HBox(pPlus2PromptTxtFld, pPlus2DisplayTxtFld, pPlus2Lbl);
		pPlus1HBox.setAlignment(Pos.CENTER_RIGHT);
		pPlus2HBox.setAlignment(Pos.CENTER_LEFT);
		pPlus1HBox.setSpacing(10);
		pPlus2HBox.setSpacing(10);
		HBox anteBet1HBox = new HBox(anteBet1Lbl, anteBet1DisplayTxtFld, anteBet1PromptTxtFld);
		HBox anteBet2HBox = new HBox(anteBet2PromptTxtFld, anteBet2DisplayTxtFld, anteBet2Lbl);
		anteBet1HBox.setAlignment(Pos.CENTER_RIGHT);
		anteBet2HBox.setAlignment(Pos.CENTER_LEFT);
		anteBet1HBox.setSpacing(10);
		anteBet2HBox.setSpacing(10);
		HBox playBet1HBox = new HBox(playBet1Lbl, playBet1DisplayTxtFld, playBet1PromptTxtFld);
		playBet1HBox.setAlignment(Pos.CENTER_LEFT);
		HBox.setMargin(playBet1Lbl, new Insets(0,0,0,25));

		HBox playBet2HBox = new HBox(playBet2PromptTxtFld, playBet2DisplayTxtFld, playBet2Lbl);
		playBet2HBox.setAlignment(Pos.CENTER_RIGHT);
		HBox.setMargin(playBet2DisplayTxtFld, new Insets(0,0,0,0));

		playBet2HBox.setAlignment(Pos.CENTER_LEFT);
		playBet1HBox.setSpacing(10);
		playBet2HBox.setSpacing(10);

		VBox player1BetsVBox = new VBox(pPlus1HBox,anteBet1HBox,playBet1HBox);
		VBox player2BetsVBox = new VBox(pPlus2HBox,anteBet2HBox,playBet2HBox);
		VBox.setMargin(player1BetsVBox, new Insets(0,20,10,0));
		VBox.setMargin(player2BetsVBox, new Insets(0,0,10,20));	
		player1BetsVBox.setSpacing(20);
		player2BetsVBox.setSpacing(20);

		HBox player1TotalMoneyHBox = new HBox(totalWinnings1Lbl, totalWinnings1TxtFld);
		HBox player2TotalMoneyHBox = new HBox(totalWinnings2Lbl, totalWinnings2TxtFld);
		player1TotalMoneyHBox.setSpacing(10);
		player2TotalMoneyHBox.setSpacing(10);

		player1TotalMoneyHBox.setAlignment(Pos.CENTER_RIGHT);
		player2TotalMoneyHBox.setAlignment(Pos.CENTER_LEFT);

		HBox totalMoneyAndBets1HBox =  new HBox(player1TotalMoneyHBox, player1BetsVBox);
		HBox totalMoneyAndBets2HBox =  new HBox(player2BetsVBox, player2TotalMoneyHBox);

		totalMoneyAndBets1HBox.setSpacing(40);
		totalMoneyAndBets2HBox.setSpacing(40);
		totalMoneyAndBets1HBox.setAlignment(Pos.CENTER_RIGHT);
		totalMoneyAndBets2HBox.setAlignment(Pos.CENTER_LEFT);
		VBox.setMargin(totalMoneyAndBets1HBox, new Insets(0,20,10,0));
		VBox.setMargin(totalMoneyAndBets2HBox, new Insets(0,0,10,20));	

		VBox btnsVBox1 = new VBox(playBtn1,foldBtn1);
		VBox btnsVBox2 = new VBox(playBtn2,foldBtn2);
		btnsVBox1.setAlignment(Pos.CENTER);
		btnsVBox2.setAlignment(Pos.CENTER);
		btnsVBox1.setSpacing(20);
		btnsVBox2.setSpacing(20);

		HBox playerCards1HBox = new HBox(firstPlayerCard1, firstPlayerCard2, firstPlayerCard3);
		HBox playerCards2HBox = new HBox(secondPlayerCard1, secondPlayerCard2, secondPlayerCard3);
		HBox player1LblHBox = new HBox(player1Lbl);
		HBox player2LblHBox = new HBox(player2Lbl);
		player1LblHBox.setAlignment(Pos.CENTER);
		player2LblHBox.setAlignment(Pos.CENTER);
		VBox playerCards1AndLabelVBox = new VBox(player1LblHBox,playerCards1HBox);
		VBox playerCards2AndLabelVBox = new VBox(player2LblHBox,playerCards2HBox);
		VBox.setMargin(playerCards1HBox, new Insets(0,20,0,0));
		VBox.setMargin(playerCards2HBox, new Insets(0,0,0,20));
		VBox.setMargin(player1LblHBox, new Insets(10,0,0,0));
		VBox.setMargin(player2LblHBox, new Insets(10,0,0,0));
		playerCards1AndLabelVBox.setSpacing(20);
		playerCards2AndLabelVBox.setSpacing(20);
		playerCards1HBox.setSpacing(10);
		playerCards2HBox.setSpacing(10);

		HBox playerCardsAndBtns1HBox = new HBox(btnsVBox1,playerCards1AndLabelVBox);
		HBox playerCardsAndBtns2HBox = new HBox(playerCards2AndLabelVBox, btnsVBox2);
		HBox.setMargin(btnsVBox1, new Insets(20, 30, 30, 50));
		HBox.setMargin(btnsVBox2, new Insets(20, 50, 30, 30));
		playerCardsAndBtns1HBox.setSpacing(30);
		playerCardsAndBtns2HBox.setSpacing(30);

		VBox player1VBox = new VBox(playerCardsAndBtns1HBox, totalMoneyAndBets1HBox); 
		VBox player2VBox = new VBox(playerCardsAndBtns2HBox, totalMoneyAndBets2HBox);

		String cssLayout = "-fx-border-color: black;\n" +
				"-fx-border-insets: 5;\n" +
				"-fx-border-width: 3;\n" + 
				"-fx-font-size: 14;\n" +
				"-fx-font-color: white;\n";
		player1VBox.setSpacing(20);
		player2VBox.setSpacing(20);
		player1VBox.setStyle(cssLayout);
		player2VBox.setStyle(cssLayout);

		HBox playersHBox = new HBox(player1VBox,player2VBox);
		playersHBox.setAlignment(Pos.CENTER);
		playersHBox.setSpacing(30);
		//playersHBox.setStyle(cssLayout);

		playersHBox.setDisable(true);

		return playersHBox;	
	}
}
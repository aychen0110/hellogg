package com.example.zhen_hellogg;


import com.google.android.glass.app.Card;
import com.google.android.glass.touchpad.Gesture;
import com.google.android.glass.touchpad.GestureDetector;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;



public class MainActivity extends Activity {
private int MAX_NUM_OF_CARDS = 6;
private int numOfCards = 0;
private int cardTracker = 0;

private View[] views = new View[MAX_NUM_OF_CARDS];
public Card[] cards = new Card[MAX_NUM_OF_CARDS];

Gesture gesture; //import com.google.android.glass.touchpad.Gesture;
GestureDetector gesDect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);
		gesDect = createGestureDetector(this);

		setupCards();
		setupViews();
		

		setContentView(views[0]);
	}

	
	private GestureDetector createGestureDetector(Context context) {
	    GestureDetector gestureDetector = new GestureDetector(context);
	        //Create a base listener for generic gestures
	        gestureDetector.setBaseListener( new GestureDetector.BaseListener() {
	            @Override
	            public boolean onGesture(Gesture gesture) {
	                if (gesture == Gesture.TAP) {
	                    // do something on tap
	                    return true;
	                } else if (gesture == Gesture.TWO_TAP) {
	                    // do something on two finger tap
	                    return true;
	                } else if (gesture == Gesture.SWIPE_RIGHT) {
	                    // do something on right (forward) swipe
	                	cardTracker++;
	            		setContentView(views[cardTracker % numOfCards]);
	                    return true;
	                } else if (gesture == Gesture.SWIPE_LEFT) {
	                    // do something on left (backwards) swipe
	                	cardTracker--;
	                	if (cardTracker < 0){
	                		cardTracker = numOfCards - 1;
	                	}
	                	setContentView(views[cardTracker % numOfCards]);
	                    return true;
	                }
	                return false;
	            }
	        });
	        gestureDetector.setFingerListener(new GestureDetector.FingerListener() {
	            @Override
	            public void onFingerCountChanged(int previousCount, int currentCount) {
	              // do something on finger count changes
	            }
	        });
	        gestureDetector.setScrollListener(new GestureDetector.ScrollListener() {
	            @Override
	            public boolean onScroll(float displacement, float delta, float velocity) {
					return true;
	                // do something on scrolling
	            }
	        });
	        return gestureDetector;
	    }

	    /*
	     * Send generic motion events to the gesture detector
	     */
	    @Override
	    public boolean onGenericMotionEvent(MotionEvent event) {
	        if (gesDect != null) {
	            return gesDect.onMotionEvent(event);
	        }
	        return false;
	    }

	
	private void setupCards(){
		Card card;
		numOfCards = 0;
		card= new Card(this);
		card.setText("Hello GG!"); 
		card.setFootnote("Greetings from Angie"); 
		cards[numOfCards] = card;
		numOfCards++;

		card= new Card(this);
		card.setText("Hello GG! Feb 24, 2014"); 
		card.setFootnote("This is the second card"); 
		cards[numOfCards] = card;
		numOfCards++;
		
		card= new Card(this);
		card.setText("Again, Hello GG!"); 
		card.setFootnote("This is the third card"); 
		card.setImageLayout(Card.ImageLayout.FULL);
		card.addImage(R.drawable.angieprofilepic);

		cards[numOfCards] = card;
		numOfCards++;
		
		card= new Card(this);
		card.setText("Sublime -- I just painted this!"); 
		card.setFootnote("Painted by Angie on Feb 24"); 
		card.setImageLayout(Card.ImageLayout.FULL);
		card.addImage(R.drawable.mypainting);

		cards[numOfCards] = card;
		numOfCards++;
	}
	
	private void setupViews(){
		for (int i = 0; i < numOfCards; i++){
			views[i] = cards[i].toView();
		}
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}



/*Portions of this page are modifications based on work created and 
 * shared by Google and used according to terms described in the
 *  Creative Commons 3.0 Attribution License.
 */


package ImageViewScroll;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.byanton.gameslot.MainActivity;
import com.byanton.gameslot.R;

public class ImageViewScroll extends FrameLayout {
    private static int ANIMATION_DUR = 150;
    ImageView currentimage,nextimage;
    MainActivity eventEnd;

    int last_result = 0, old_value = 0;

    public void setEventEnd(MainActivity eventEnd) {
        this.eventEnd = eventEnd;
    }

    public ImageViewScroll(@NonNull Context context) {
        super(context);
        init(context);
    }

    public ImageViewScroll(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.imageviewscroll,this);
        currentimage = (ImageView)getRootView().findViewById(R.id.currentview);
        nextimage = (ImageView)getRootView().findViewById(R.id.nextview);

        nextimage.setTranslationY(getHeight());

    }

    public void setValueRandom (final int image, final int rotate_count){
        currentimage.animate().translationY(-getHeight()).setDuration(ANIMATION_DUR).start();
        nextimage.setTranslationY(nextimage.getHeight());
        nextimage.animate().translationY(0).setDuration(ANIMATION_DUR).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                setImage(currentimage,old_value%3);
                currentimage.setTranslationY(0);
                if(old_value != rotate_count){
                    setValueRandom(image,rotate_count);
                    old_value++;
                } else {
                    last_result = 0;
                    old_value = 0;
                    setImage(nextimage,image);
                    eventEnd.eventEnd(image%3,rotate_count);
                }

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void setImage(ImageView imageview, int value) {
        if (value == Utill.slot1){
            imageview.setImageResource(R.drawable.slot1);
        }
        else if (value == Utill.slot2){
            imageview.setImageResource(R.drawable.slot2);
        }
        else if (value == Utill.slot3){
            imageview.setImageResource(R.drawable.slot3);
        }
        else if (value == Utill.slot4){
            imageview.setImageResource(R.drawable.slot4);
        }
        else if (value == Utill.slot5){
            imageview.setImageResource(R.drawable.slot5);
        }
        else if (value == Utill.slot6){
            imageview.setImageResource(R.drawable.slot6);
        }
        else if (value == Utill.slot7){
            imageview.setImageResource(R.drawable.slot7);
        }
        imageview.setTag(value);
        last_result = value;
    }

    public int getValue(){
        return Integer.parseInt(nextimage.getTag().toString());
    }



}

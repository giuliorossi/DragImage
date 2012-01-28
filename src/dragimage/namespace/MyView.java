package dragimage.namespace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

public class MyView extends View
{
	private int x=100;
	private int y=100;
	private Bitmap img=null;
	public boolean dragOn=false;
	
	private int offsetx;
	private int offsety;
	
	// 4) inizializzare la bitmap da una risorsa drawable
	public MyView(Context context)
	{
		super(context);
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		img = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		offsetx = (img.getWidth())/2;
		offsety = (img.getHeight())/2;
	}
	
	// 5) Sovrascrivere il metodo onDraw della bitmap per ridisegnare l'immagine
	
	protected void onDraw(Canvas canvas)
	{
		canvas.drawBitmap(img, x, y, null);
	}
	
	// 7) Riscrivere il metodo onTouchEvent
	
	public boolean onTouchEvent(MotionEvent event) 
	{
		int eventaction = event.getAction();
		int touchx = (int)event.getX();
		int touchy = (int)event.getY();
		
		switch (eventaction)
		{
			case MotionEvent.ACTION_DOWN:
			{
				if ((touchx > x && touchx < x + img.getWidth()) && (touchy > y && touchy < y + img.getHeight()))
				{
					dragOn=true;
				}
				break;	
			}
			case MotionEvent.ACTION_MOVE:
			{
				x=touchx-offsetx;
				y=touchy-offsety;
				//x=touchx;
				//y=touchy;
				invalidate();
				break;
			}
			case MotionEvent.ACTION_UP:
			{
				dragOn=false;
			}
		
		}
		return true;
	}
		
}

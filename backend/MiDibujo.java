import android.app.*;
import android.content.*;
import android.graphics.*;
import android.os.*;
import android.view.*;
public class MiDibujo extends Activity{
	public void onCreate(Bundle b){
		super.onCreate(b);
		MiPaint mp = new MiPaint(this);
		setContentView(mp);
	}
	class MiPaint extends View{
		float x = 50, y = 50;
		String a = "accion";
		Path p = new Path();
		public MiPaint(Context context){
			super(context);
		}
		protected void onDraw(Canvas canvas){
			canvas.drawColor(Color.rgb(255, 255, 255));
			Paint pinta = new Paint();
			pinta.setStyle(Paint.Style.STROKE);
			pinta.setStrokeWidth(5);
			pinta.setColor(Color.BLUE);
			if(a=="down") p.moveTo(x, y);
			if(a=="move") p.lineTo(x, y);
			canvas.drawPath(p, pinta);
		}
		public boolean ontTouchEvent(MotionEvent evento){
			x = evento.getX();
			y = evento.getY();
			if(evento.getAction() == MotionEvent.ACTION_DOWN) a = "down";
			if(evento.getAction()==MotionEvent.ACTION_MOVE) a = "move";
			invalidate();
			return true;
		}
	}
}
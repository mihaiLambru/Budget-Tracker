package com.example.budgettracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class GraficPie extends View {
    private float totalVenituri;
    private float totalSalariu;
    private float totalChirii;
    private float totalDividende;
    private float totalProiecte;

    private float procentSalariu;
    private float procentChirii;
    private float procentDividende;
    private float procentProiecte;
    Paint paint;

    public GraficPie(Context context) {
        super(context);
    }

    public GraficPie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GraficPie(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public GraficPie(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setAll(float totalVenituri, float totalSalariu, float totalChirii, float totalDividende, float totalProiecte) {
        this.totalVenituri = totalVenituri;
        this.totalSalariu = totalSalariu;
        this.totalChirii = totalChirii;
        this.totalDividende = totalDividende;
        this.totalProiecte = totalProiecte;
    }

    public void setAll(float procentSalariu, float procentChirii, float procentDividende, float procentProiecte) {
        this.procentSalariu = procentSalariu;
        this.procentChirii = procentChirii;
        this.procentDividende = procentDividende;
        this.procentProiecte = procentProiecte;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        float procentDividende, procentProiecte, procentSalariu, procentChirii;

        float gradeSalariu, gradeChirii, gradeProiecte, gradeDividende;
        float gradeInceputArc = 0;
        float gradeFinalArc = 0;
        Paint paint = new Paint();


       if (totalSalariu > 0) {
            Log.w("Salarii", ""+totalSalariu);
            procentSalariu = totalSalariu * 100 / totalVenituri;
            gradeSalariu = procentSalariu * 360 / 100;
            gradeFinalArc += gradeSalariu;

            paint.setColor(getContext().getResources().getColor(R.color.color_salariu));
            paint.setStyle(Paint.Style.FILL);

            canvas.drawArc(25, 25, 375, 375, gradeInceputArc, gradeFinalArc, true, paint);
            Log.w("Salariu", gradeInceputArc + " " + gradeFinalArc);
            gradeInceputArc = gradeFinalArc;

        }

        if (totalChirii > 0) {
            procentChirii = totalChirii * 100 / totalVenituri;
            gradeChirii = procentChirii * 360 / 100;
            gradeFinalArc += gradeChirii;


            paint.setColor(getContext().getResources().getColor(R.color.color_chirie));
            paint.setStyle(Paint.Style.FILL);

            canvas.drawArc(25, 25, 375, 375, gradeInceputArc, gradeChirii, true, paint);
            //invalidate();
            Log.w("Chirii", gradeInceputArc + " " + gradeFinalArc);
            gradeInceputArc = gradeFinalArc;


        }

        if (totalDividende > 0) {
            Log.w("Dividende", ""+totalDividende);
            procentDividende = totalDividende * 100 / totalVenituri;
            gradeDividende = procentDividende * 360 / 100;
            gradeFinalArc += gradeDividende;

            paint.setColor(getContext().getResources().getColor(R.color.color_dividend));
            paint.setStyle(Paint.Style.FILL);

            canvas.drawArc(25, 25, 375, 375, gradeInceputArc, gradeDividende, true, paint);
            Log.w("Dividende", gradeInceputArc + " " + gradeFinalArc);
            gradeInceputArc = gradeFinalArc;


        }

        if (totalProiecte > 0) {
            procentProiecte = totalProiecte * 100 / totalVenituri;
            gradeProiecte= procentProiecte * 360 / 100;
            gradeFinalArc += gradeProiecte;

            paint.setColor(getContext().getResources().getColor(R.color.color_proiecte));
            paint.setStyle(Paint.Style.FILL);

            canvas.drawArc(25, 25, 375, 375, gradeInceputArc, gradeProiecte, true, paint);
            Log.w("Proiecte", gradeInceputArc + " " + gradeFinalArc);
         //   gradeInceputArc = gradeFinalArc;

        }

    }
}

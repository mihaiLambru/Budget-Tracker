package com.example.budgettracker.Database.AsyncOperations;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.budgettracker.Database.DAO.InregistrareDAO;
import com.example.budgettracker.Database.Entities.Cont;
import com.example.budgettracker.Database.Entities.Inregistrare;
import com.example.budgettracker.Database.LocalDatabase;
import com.example.budgettracker.GraficPie;
import com.example.budgettracker.R;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AsyncWrapperInregistrare {

    private InregistrareDAO inregistrareDAO;

    public AsyncWrapperInregistrare(Context context) {
        LocalDatabase localDatabase = LocalDatabase.getInstance(context);
        inregistrareDAO = localDatabase.getInregistrareDAO();
    }

    private static class InsertInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private InsertInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }

        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.insert(inregistrari[0]);
            return null;
        }
    }

    private static class UpdateInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private UpdateInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }
        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.update(inregistrari[0]);
            return null;
        }
    }

    private static class DeleteInregistrareAsyncTask extends AsyncTask<Inregistrare, Void, Void> {
        private InregistrareDAO inregistrareDAO;

        private DeleteInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }
        @Override
        protected Void doInBackground(Inregistrare... inregistrari) {
            inregistrareDAO.delete(inregistrari[0]);
            return null;
        }
    }

    private static class SelectInregistrareAsyncTask extends AsyncTask<Integer, Void, Inregistrare> {
        private InregistrareDAO inregistrareDAO;

        private SelectInregistrareAsyncTask(InregistrareDAO inregistrareDAO) {
            this.inregistrareDAO = inregistrareDAO;
        }

        @Override
        protected Inregistrare doInBackground(Integer... id) {
            Inregistrare inregistrare = inregistrareDAO.select(id[0]);
            return inregistrare;
        }

        @Override
        protected void onPostExecute(Inregistrare inregistrare) {
            //de revenit
            super.onPostExecute(inregistrare);
        }
    }

    private static class SelectListaInregistrariAsyncTask extends AsyncTask<Cont, Void, List<Inregistrare>> {
        private InregistrareDAO inregistrareDAO;
        private Context context;
        private Cont cont;
        private LinearLayout linearLayoutContentsCheltuieli;
        private LinearLayout linearLayoutContentsVenituri;

        private SelectListaInregistrariAsyncTask(InregistrareDAO inregistrareDAO, Context context) {
            this.inregistrareDAO = inregistrareDAO;
            this.context = context;
        }

        @Override
        protected List<Inregistrare> doInBackground(Cont... conturi) {
            this.cont = conturi[0];
            return inregistrareDAO.selectLista(conturi[0].getIdCont());
        }

        @Override
        protected void onPostExecute(List<Inregistrare> inregistrari) {
            super.onPostExecute(inregistrari);

            float totalCheltuieli = 0;
            float totalVenituri = 0;


            float totalAlimente, totalCumparaturi, totalLocuinta, totalTransport, totalVehicul, totalDivertisment;
            float totalSalariu, totalChirii, totalProiecte, totalDividende;

            totalAlimente = totalCumparaturi = totalLocuinta = totalTransport = totalVehicul =
                    totalDivertisment = totalSalariu = totalChirii = totalProiecte = totalDividende = 0;

            for (Inregistrare inregistrare : inregistrari) {
                if (inregistrare.getTipInregistrare() == Inregistrare.TipInregistrare.VENIT) {

                    totalVenituri += inregistrare.getSuma();
                    switch (inregistrare.getCategorieVenit()) {
                        case SALARIU:
                            totalSalariu += inregistrare.getSuma();
                            break;
                        case CHIRII:
                            totalChirii += inregistrare.getSuma();
                            break;
                        case DIVIDENDE:
                            totalDividende += inregistrare.getSuma();
                            break;
                        case PROIECTE:
                            totalProiecte += inregistrare.getSuma();
                            break;
                    }

                } else if (inregistrare.getTipInregistrare() == Inregistrare.TipInregistrare.CHELTUIALA) {
                    totalCheltuieli += inregistrare.getSuma();

                    switch (inregistrare.getCategorieCheltuiala()) {
                        case ALIMENTE:
                            totalAlimente += inregistrare.getSuma();
                            break;
                        case LOCUINTA:
                            totalLocuinta += inregistrare.getSuma();
                            break;
                        case VEHICUL:
                            totalVehicul += inregistrare.getSuma();
                            break;
                        case DIVERTISMENT:
                            totalDivertisment += inregistrare.getSuma();
                            break;
                        case TRANSPORT:
                            totalTransport += inregistrare.getSuma();
                            break;
                        case CUMPARATURI:
                            totalCumparaturi += inregistrare.getSuma();
                            break;
                    }
                }
            }



            int nrCategoriiVenit, nrCategoriiCheltuiala;
            nrCategoriiCheltuiala = nrCategoriiVenit = 0;


            float[] vectorVenituriPeCategorii = new float[]{totalDividende, totalProiecte, totalSalariu, totalChirii};
            float[] vectorCheltuieliPeCategorii = new float[]{totalAlimente, totalCumparaturi, totalDivertisment, totalLocuinta, totalTransport, totalVehicul};

            for (int i = 0; i < 4; ++i) {
                if (vectorVenituriPeCategorii[i] != 0) {
                    ++nrCategoriiVenit;
                }
            }

            for (int i = 0; i < 6; ++i) {
                if (vectorCheltuieliPeCategorii[i] != 0) {
                    ++nrCategoriiCheltuiala;
                }
            }

            float procentDividende, procentProiecte, procentSalariu, procentChirii, procentAlimente,
                    procentCumparaturi, procentDivertisment, procentLocuinta, procentTransport, procentVehicul;

            PieChart pieChartCheltuieli = ((Activity) context)
                    .findViewById(R.id.pieChartCheltuieli);
            pieChartCheltuieli.clearChart();
            linearLayoutContentsCheltuieli = ((Activity)context)
                    .findViewById(R.id.linearLayoutContentsCheltuieli);
            linearLayoutContentsCheltuieli.removeAllViews();


            if (totalAlimente > 0) {
                procentAlimente = totalAlimente * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Alimente",
                                procentAlimente,
                                context.getResources().getColor(R.color.color_alimente)
                        ));
                adaugaCategorieInLegenda("Alimente", linearLayoutContentsCheltuieli,procentAlimente);
            }
            if (totalCumparaturi > 0) {
                procentCumparaturi = totalCumparaturi * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Cumparaturi",
                                procentCumparaturi,
                                context.getResources().getColor(R.color.color_cumparaturi)
                        ));
                adaugaCategorieInLegenda("Cumparaturi", linearLayoutContentsCheltuieli,procentCumparaturi);
            }

            if (totalDivertisment > 0) {
                procentDivertisment = totalDivertisment * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Divertisment",
                                procentDivertisment,
                                context.getResources().getColor(R.color.color_divertisment)
                        ));
                adaugaCategorieInLegenda("Divertisment", linearLayoutContentsCheltuieli, procentDivertisment);
            }

            if (totalLocuinta > 0) {
                procentLocuinta = totalLocuinta * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Locuinta",
                                procentLocuinta,
                                context.getResources().getColor(R.color.color_locuinta)
                        ));
                adaugaCategorieInLegenda("Locuinta", linearLayoutContentsCheltuieli, procentLocuinta);
            }

            if (totalVehicul > 0) {
                procentVehicul = totalVehicul * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Vehicul",
                                procentVehicul,
                                context.getResources().getColor(R.color.color_vehicul)
                        ));
                adaugaCategorieInLegenda("Vehicul", linearLayoutContentsCheltuieli, procentVehicul);
            }

            if (totalTransport > 0) {
                procentTransport = totalTransport * 100 / totalCheltuieli;
                pieChartCheltuieli.addPieSlice(
                        new PieModel(
                                "Transport",
                                procentTransport,
                                context.getResources().getColor(R.color.color_transport)
                        ));
                adaugaCategorieInLegenda("Transport", linearLayoutContentsCheltuieli, procentTransport);
            }

            linearLayoutContentsVenituri = ((Activity) context).findViewById(R.id.linearLayoutContentsVenituri);
            linearLayoutContentsVenituri.removeAllViews();


            GraficPie graficPie = ((Activity) context).findViewById(R.id.pieChartVenituri);
            graficPie.setAll(totalVenituri, totalSalariu, totalChirii, totalDividende, totalProiecte);


            if (totalSalariu > 0) {
                procentSalariu = totalSalariu * 100 / totalVenituri;
                adaugaCategorieInLegenda("Salarii", linearLayoutContentsVenituri, procentSalariu);
            }

            if (totalChirii > 0) {
                procentChirii = totalChirii * 100 / totalVenituri;
                adaugaCategorieInLegenda("Chirii", linearLayoutContentsVenituri, procentChirii);
            }

            if (totalProiecte > 0) {
                procentProiecte = totalProiecte * 100 / totalVenituri;
                adaugaCategorieInLegenda("Proiecte", linearLayoutContentsVenituri, procentProiecte);
            }

            if (totalDividende > 0) {
                procentDividende = totalDividende * 100 / totalVenituri;
                adaugaCategorieInLegenda("Dividende", linearLayoutContentsVenituri, procentDividende);
            }

            TextView textViewTotalCheltuieli = ((Activity) context).findViewById(R.id.textViewPieCheltuieli);
            TextView textViewTotalVenituri = ((Activity) context).findViewById(R.id.textViewPieVenituri);

            String moneda = cont.getMoneda();
            String stringCheltuieli = "Total cheltuieli: " + String.format("%.2f", totalCheltuieli) + " " + moneda;
            String stringVenituri = "Total venituri: " + String.format("%.2f", totalVenituri) + " " + moneda;

            SpannableString spannableCheltuieli = new SpannableString(stringCheltuieli);
            SpannableString spannableVenituri = new SpannableString(stringVenituri);

            if (moneda.equals("RON")) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan
                        (context.getResources().getColor(R.color.color_ron));
                StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                spannableCheltuieli.setSpan(foregroundColorSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableCheltuieli.setSpan(styleSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalCheltuieli.setText(spannableCheltuieli);

                spannableVenituri.setSpan(foregroundColorSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableVenituri.setSpan(styleSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalVenituri.setText(spannableVenituri);
            }
            else if (cont.getMoneda().equals("EUR")) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.color_eur));
                StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                spannableCheltuieli.setSpan(foregroundColorSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableCheltuieli.setSpan(styleSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalCheltuieli.setText(spannableCheltuieli);

                spannableVenituri.setSpan(foregroundColorSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableVenituri.setSpan(styleSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalVenituri.setText(spannableVenituri);
            }
            else if (cont.getMoneda().equals("USD")) {
                ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(context.getResources().getColor(R.color.color_usd));
                StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);
                spannableCheltuieli.setSpan(foregroundColorSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableCheltuieli.setSpan(styleSpan, stringCheltuieli.length() - 4, stringCheltuieli.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalCheltuieli.setText(spannableCheltuieli);

                spannableVenituri.setSpan(foregroundColorSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableVenituri.setSpan(styleSpan, stringVenituri.length() - 4, stringVenituri.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                textViewTotalVenituri.setText(spannableVenituri);
            }
        }

        public void adaugaCategorieInLegenda(String categorie, LinearLayout linearLayoutContents, float procent) {

            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(400, 50));
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);

            View view = new View(context);
            GradientDrawable drawable = (GradientDrawable) context.getDrawable(R.drawable.circle);
            TextView textView = new TextView(context);

            String[] procentString = Float.toString(procent).split("\\.");
            int digits = procentString[0].length();

            StringBuilder string = new StringBuilder(categorie + " " + String.format("%.2f", procent) + "%");

            SpannableString spannableString = new SpannableString(string);
            ForegroundColorSpan foregroundColorSpan;
            StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);

            switch (categorie) {
                case "Alimente":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_alimente));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_alimente));
                    break;
                case "Cumparaturi":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_cumparaturi));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_cumparaturi));
                    break;
                case "Divertisment":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_divertisment));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_divertisment));
                    break;
                case "Locuinta":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_locuinta));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_locuinta));
                    break;
                case "Vehicul":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_vehicul));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_vehicul));
                    break;
                case "Transport":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_transport));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_transport));
                    break;
                case "Salarii":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_salariu));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_salariu));
                    break;
                case "Chirii":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_chirie));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_chirie));
                    break;
                case "Proiecte":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_proiecte));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits, string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_proiecte));
                    break;
                case "Dividende":
                    foregroundColorSpan = new ForegroundColorSpan
                            (context.getResources().getColor(R.color.color_dividend));
                    spannableString.setSpan(foregroundColorSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    spannableString.setSpan(styleSpan, string.length() - 4 - digits , string.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                    textView.setText(spannableString);
                    drawable.setColor(context.getResources().getColor(R.color.color_dividend));
                    break;
            }

            view.setBackground(drawable);
            view.setLayoutParams(new LinearLayout.LayoutParams(40,40));

            textView.setTextColor(context.getResources().getColor(R.color.black));
            textView.setTextSize(12);

            textView.setLayoutParams(new LinearLayout.LayoutParams(600, 50));
            textView.setPadding(10, -7, 0, 0);

            linearLayout.addView(view);
            linearLayout.addView(textView);

            linearLayoutContents.addView(linearLayout);
        }
    }

    public void insert(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.InsertInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void update(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.UpdateInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void delete(Inregistrare inregistrare) {
        new AsyncWrapperInregistrare.DeleteInregistrareAsyncTask(inregistrareDAO).execute(inregistrare);
    }

    public void select(Integer id) {
        new AsyncWrapperInregistrare.SelectInregistrareAsyncTask(inregistrareDAO).execute(id);
    }

    public void selectLista(Cont cont, Context context) {
        new AsyncWrapperInregistrare.SelectListaInregistrariAsyncTask(inregistrareDAO, context).execute(cont);
    }
}

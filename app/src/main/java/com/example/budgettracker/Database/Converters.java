package com.example.budgettracker.Database;

import androidx.room.TypeConverter;

import com.example.budgettracker.Database.Entities.Inregistrare;

public class Converters {
    @TypeConverter
    public static String tipInregistrareToString(Inregistrare.TipInregistrare tipInregistrare) {

        return tipInregistrare.toString();
    }

    @TypeConverter
    public static Inregistrare.TipInregistrare stringToTipInregistrare(String inregistrare) {
        return Inregistrare.TipInregistrare.valueOf(inregistrare);
    }


    @TypeConverter
    public static String categorieCheltuialaToString(Inregistrare.CategorieCheltuiala categorieCheltuiala) {
        if (categorieCheltuiala == null) {
            return "NULL";
        }
        return categorieCheltuiala.toString();
    }

    @TypeConverter
    public static Inregistrare.CategorieCheltuiala stringToCategorieCheltuiala(String categorie) {
        return Inregistrare.CategorieCheltuiala.valueOf(categorie);
    }


    @TypeConverter
    public static String categorieVenitToString(Inregistrare.CategorieVenit categorieVenit) {
        if (categorieVenit== null) {
            return "NULL";
        }
        return categorieVenit.toString();
    }

    @TypeConverter
    public static Inregistrare.CategorieVenit stringToCategorieVenit(String categorie) {
        return Inregistrare.CategorieVenit.valueOf(categorie);
    }
}
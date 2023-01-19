package com.mediscreen.diagnostic.model;

public enum DiabetesRiskLevel
{

    NONE("None"),
    BORDERLINE("Borderline"),
    IN_DANGER("In Danger"),
    EARLY_ONSET("Early Onset");

    String view;

    DiabetesRiskLevel(String s)
    {
        view = s;
    }

    public String toString()
    {
        return view;
    }
}
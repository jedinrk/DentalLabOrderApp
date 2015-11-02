package com.example.naveen.dentalorder.model;

import android.content.Context;

/**
 * Created by Naveen on 11/1/2015.
 */
public class LabOrderWizardMobelB extends AbstractWizardModel {
    public LabOrderWizardMobelB(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(new CustomerInfoPage(this, "Order #1").setRequired(true),
                new MultipleFixedChoicePage(this, "Type of Works")
                .setChoices("Crown",
                        "Bridge",
                        "Veneer",
                        "Cantilever Bridge",
                        "Full Metal",
                        "Onlay /Inlay",
                        "Post Crown",
                        "Post Core",
                        "Maryland Bridge",
                        "Precision Attachment").setRequired(true),
                new MultipleFixedChoicePage(this, "Type of Alloy Material")
                        .setChoices("Zirconia",
                                "Non-Precious",
                                "Semi-Precious",
                                "Yellow Gold Precious",
                                "H-White Gold Precious",
                                "Empress / Emax",
                                "Ceramage Composite (Shofu)").setRequired(true),
                new MultipleFixedChoicePage(this, "Implant Restoration's")
                        .setChoices("Screw Retain",
                                "3I Implant",
                                "Bicon",
                                "Astra",
                                "Zimmer",
                                "Cemented",
                                "Ankylos/xive",
                                "Biocare",
                                "Osstem",
                                "Others").setRequired(true));
    }
}

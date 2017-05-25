package ch.elexis.core.ui.eigenartikel;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "ch.elexis.core.ui.eigenartikel.messages"; //$NON-NLS-1$
	
	public static String EigenartikelDisplay_articleName = null;
	public static String EigenartikelDisplay_actualOnStockPacks;
	public static String EigenartikelDisplay_actualOnStockPieces;
	public static String EigenartikelDisplay_buyPrice;
	public static String EigenartikelDisplay_dealer;
	public static String EigenartikelDisplay_displayTitle;
	public static String EigenartikelDisplay_group;
	public static String EigenartikelDisplay_maxOnStock;
	public static String EigenartikelDisplay_minOnStock;
	public static String EigenartikelDisplay_PiecesPerDose;
	public static String EigenartikelDisplay_PiecesPerPack;
	public static String EigenartikelDisplay_pleaseChooseDealer;
	public static String EigenartikelDisplay_sellPrice;
	public static String EigenartikelDisplay_typ;
	public static String EigenartikelDisplay_Pharmacode;
	public static String Eigenartikel_WarningPharmacodeChange_Title;
	public static String Eigenartikel_WarningPharmacodeChange;
	public static String EigenartikelDisplay_productName;
	public static String EigenartikelDisplay_productNumber;
	public static String EigenartikelDisplay_gtin;
	public static String EigenartikelDisplay_atcCode;
	public static String EigenartikelComposite_lblNewLabel_text;
	public static String EigenartikelComposite_grpDrugPackages_text;
	public static String EigenartikelComposite_lblProductName_text;
	public static String EigenartikelComposite_lblProductType_text;
	public static String EigenartikelComposite_lblInternalName_text;
	public static String EigenartikelComposite_lblPharmacode_text;
	public static String EigenartikelComposite_linkProvider_text;
	public static String EigenartikelComposite_lblMeasurementUnit_text;
	public static String EigenartikelComposite_lblPackagesstring_text;
	public static String EigenartikelComposite_btnCheckButton_text;
	public static String EigenartikelComposite_lblPackagesstring_toolTipText;
	public static String EigenartikelComposite_lblVerkaufseinheit_text;
	public static String EigenartikelComposite_stockGroup_text;
	public static String EigenartikelComposite_newArticle_text;
	public static String EigenartikelComposite_deleteArticle_text;
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
	
	private Messages(){}
}

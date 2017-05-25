package ch.elexis.core.ui.eigenartikel;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import ch.elexis.core.eigenartikel.Eigenartikel;
import ch.elexis.core.ui.icons.Images;
import ch.elexis.core.ui.locks.IUnlockable;
import ch.elexis.core.ui.views.controls.StockDetailComposite;

public class EigenartikelComposite extends Composite implements IUnlockable {
	
	private WritableValue drugPackageEigenartikel = new WritableValue(null, Eigenartikel.class);
	

	private Button btnDeleteDrugPackage;
	private Text txtPackageSizeString;
	private Text txtGtin;
	private Text txtPharmacode;
	private Text txtPackageSizeInt;
	private Text txtExfPrice;
	private Text txtpubPrice;
	private Label lblMeasurementUnit;
	private Text txtMeasurementUnit;
	private Group grpDrugPackages;
	private Button btnHiCostAbsorption;
	private Text txtSellUnit;
	private Label lblVerkaufseinheit;
	private StockDetailComposite stockDetailComposite;
	
	private final Eigenartikel eigenartikel;
	
	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EigenartikelComposite(Composite parent, int style, Eigenartikel eigenartikel){
		super(parent, style);
		this.eigenartikel = eigenartikel;
		this.drugPackageEigenartikel.setValue(eigenartikel);
		
		setLayout(new GridLayout(2, false));
		createArticlePart();
	}
	
	private void createArticlePart(){
		grpDrugPackages = new Group(this, SWT.NONE);
		grpDrugPackages.setLayout(new GridLayout(1, false));
		grpDrugPackages.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 2, 1));
		grpDrugPackages.setText("");
		
		Composite compDpSelector = new Composite(grpDrugPackages, SWT.NONE);
		compDpSelector.setLayout(new GridLayout(3, false));
		compDpSelector.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		btnDeleteDrugPackage = new Button(compDpSelector, SWT.FLAT);
		btnDeleteDrugPackage.setText(Messages.EigenartikelComposite_deleteArticle_text);
		btnDeleteDrugPackage.setToolTipText(Messages.EigenartikelComposite_deleteArticle_text);
		btnDeleteDrugPackage.setImage(Images.IMG_DELETE.getImage());
		btnDeleteDrugPackage.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){
				if (eigenartikel != null && eigenartikel.exists()) {
					eigenartikel.delete();
					Composite p = getParent();
					dispose();
					
					if (p.getParent() instanceof ScrolledComposite) {
						ScrolledComposite sc = (ScrolledComposite) p.getParent();
						sc.setMinSize(p.computeSize(SWT.DEFAULT, SWT.DEFAULT));
						sc.layout(true, true);
					}
				}
			}
		});
		
		Composite compDpDetail = new Composite(grpDrugPackages, SWT.BORDER);
		compDpDetail.setLayout(new GridLayout(4, false));
		compDpDetail.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		Label lblGtin = new Label(compDpDetail, SWT.NONE);
		lblGtin.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblGtin.setText(Messages.EigenartikelDisplay_gtin);
		
		txtGtin = new Text(compDpDetail, SWT.BORDER);
		txtGtin.setTextLimit(20);
		txtGtin.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPharmacode = new Label(compDpDetail, SWT.NONE);
		lblPharmacode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPharmacode.setText(Messages.EigenartikelDisplay_Pharmacode);
		
		txtPharmacode = new Text(compDpDetail, SWT.BORDER);
		txtPharmacode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPackagesint = new Label(compDpDetail, SWT.NONE);
		lblPackagesint.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPackagesint.setText(Messages.EigenartikelDisplay_PiecesPerPack);
		
		txtPackageSizeInt = new Text(compDpDetail, SWT.BORDER);
		txtPackageSizeInt.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblMeasurementUnit = new Label(compDpDetail, SWT.NONE);
		lblMeasurementUnit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMeasurementUnit.setText(Messages.EigenartikelComposite_lblMeasurementUnit_text);
		
		txtMeasurementUnit = new Text(compDpDetail, SWT.BORDER);
		txtMeasurementUnit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPackagesstring = new Label(compDpDetail, SWT.NONE);
		lblPackagesstring.setToolTipText(Messages.EigenartikelComposite_lblPackagesstring_toolTipText);
		lblPackagesstring.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPackagesstring.setText(Messages.EigenartikelComposite_lblPackagesstring_text);
		
		txtPackageSizeString = new Text(compDpDetail, SWT.BORDER);
		txtPackageSizeString.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblVerkaufseinheit = new Label(compDpDetail, SWT.NONE);
		lblVerkaufseinheit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVerkaufseinheit.setText(Messages.EigenartikelComposite_lblVerkaufseinheit_text);
		
		txtSellUnit = new Text(compDpDetail, SWT.BORDER);
		txtSellUnit.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblExFactoryPrice = new Label(compDpDetail, SWT.NONE);
		lblExFactoryPrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblExFactoryPrice.setText(Messages.EigenartikelDisplay_buyPrice);
		
		txtExfPrice = new Text(compDpDetail, SWT.BORDER);
		txtExfPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblPublicPrice = new Label(compDpDetail, SWT.NONE);
		lblPublicPrice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPublicPrice.setText(Messages.EigenartikelDisplay_sellPrice);
		
		txtpubPrice = new Text(compDpDetail, SWT.BORDER);
		txtpubPrice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(compDpDetail, SWT.NONE);
		
		btnHiCostAbsorption = new Button(compDpDetail, SWT.CHECK);
		btnHiCostAbsorption.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		btnHiCostAbsorption.setText(Messages.EigenartikelComposite_btnCheckButton_text);
		
		Group stockGroup = new Group(grpDrugPackages, SWT.NONE);
		stockGroup.setText(Messages.EigenartikelComposite_stockGroup_text);
		stockGroup.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_stockGroup = new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1);
		gd_stockGroup.heightHint = 80;
		stockGroup.setLayoutData(gd_stockGroup);
		
		stockDetailComposite = new StockDetailComposite(stockGroup, SWT.NONE);
		stockDetailComposite.setArticle(eigenartikel);
		
		initDataBindings();
	}
	
	
	@Override
	protected void checkSubclass(){
		// Disable the check that prevents subclassing of SWT components
	}
	
	@Override
	public void setUnlocked(boolean unlocked){
		btnDeleteDrugPackage.setEnabled(unlocked);
		btnHiCostAbsorption.setEnabled(unlocked);
		txtGtin.setEditable(unlocked);
		txtPharmacode.setEditable(unlocked);
		txtPackageSizeInt.setEditable(unlocked);
		txtMeasurementUnit.setEditable(unlocked);
		txtPackageSizeString.setEditable(unlocked);
		txtExfPrice.setEditable(unlocked);
		txtpubPrice.setEditable(unlocked);
		txtSellUnit.setEditable(unlocked);
		stockDetailComposite.setEnabled(unlocked);
	}
	
	protected DataBindingContext initDataBindings(){
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue observeTextTxtGtinObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtGtin);
		IObservableValue drugPackageEigenartikelEANObserveDetailValue = PojoProperties
			.value(Eigenartikel.class, "EAN", String.class).observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtGtinObserveWidget,
			drugPackageEigenartikelEANObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtPackageSizeIntObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtPackageSizeInt);
		IObservableValue drugPackageEigenartikelPackungsGroesseObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "packageSize", Integer.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtPackageSizeIntObserveWidget,
			drugPackageEigenartikelPackungsGroesseObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtExfPriceObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtExfPrice);
		IObservableValue drugPackageEigenartikelEKPreisObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "exfPrice", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtExfPriceObserveWidget,
			drugPackageEigenartikelEKPreisObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtpubPriceObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtpubPrice);
		IObservableValue drugPackageEigenartikelVKPreisObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "pubPrice", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtpubPriceObserveWidget,
			drugPackageEigenartikelVKPreisObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtMeasurementUnitObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtMeasurementUnit);
		IObservableValue drugPackageEigenartikelMeasurementUnitObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "measurementUnit", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtMeasurementUnitObserveWidget,
			drugPackageEigenartikelMeasurementUnitObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtPharmacodeObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtPharmacode);
		IObservableValue drugPackageEigenartikelPharmaCodeObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "pharmaCode", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtPharmacodeObserveWidget,
			drugPackageEigenartikelPharmaCodeObserveDetailValue, null, null);
		//
		IObservableValue observeTextTxtSellUnitObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtSellUnit);
		IObservableValue drugPackageEigenartikelSellUnitObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "sellUnit", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTextTxtSellUnitObserveWidget,
			drugPackageEigenartikelSellUnitObserveDetailValue, null, null);
		//
		IObservableValue observeTooltipTextTxtPackageSizeStringObserveWidget =
			WidgetProperties.text(SWT.Modify).observe(txtPackageSizeString);
		IObservableValue drugPackageEigenartikelPackageSizeStringObserveDetailValue =
			PojoProperties.value(Eigenartikel.class, "packageSizeString", String.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeTooltipTextTxtPackageSizeStringObserveWidget,
			drugPackageEigenartikelPackageSizeStringObserveDetailValue, null, null);
		//
		org.eclipse.core.databinding.observable.value.IObservableValue observeSelectionBtnHiCostAbsorptionObserveWidget =
			org.eclipse.jface.databinding.swt.WidgetProperties.selection()
				.observe(btnHiCostAbsorption);
		org.eclipse.core.databinding.observable.value.IObservableValue drugPackageEigenartikelHealthInsuranceCostAbsorptionObserveDetailValue =
			org.eclipse.core.databinding.beans.PojoProperties
				.value(ch.elexis.core.eigenartikel.Eigenartikel.class,
					"healthInsuranceCostAbsorption", boolean.class)
				.observeDetail(drugPackageEigenartikel);
		bindingContext.bindValue(observeSelectionBtnHiCostAbsorptionObserveWidget,
			drugPackageEigenartikelHealthInsuranceCostAbsorptionObserveDetailValue, null, null);
		return bindingContext;
	}
}

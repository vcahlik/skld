package cz.cvut.fit.si1.skld.client.components.find_product_type;

import cz.cvut.fit.si1.skld.client.Fragment;
import cz.cvut.fit.si1.skld.client.Handler;
import cz.cvut.fit.si1.skld.client.NotifyType;
import cz.cvut.fit.si1.skld.client.Notifyable;
import cz.cvut.fit.si1.skld.client.domain.ExampleProductType;
import cz.cvut.fit.si1.skld.client.domain.ProductType;

import java.util.ArrayList;
import java.util.List;

public class FindProductTypeFragment extends Fragment {
    private FindProductTypeFragmentHandler handler;

    private List<ProductType> productTypes;
    private ProductType selectedProductType;

    public FindProductTypeFragment(Notifyable parent) {
        super(parent);
        productTypes = new ArrayList<>();
    }

    @Override
    public Handler makeHandler() {
        this.handler = new FindProductTypeFragmentHandler();
        handler.setOwner(this);
        return handler;
    }

    public ProductType getSelected() {
        return selectedProductType;
    }

    protected void setSelectedProductType(ProductType selectedProductType) {
        if (this.selectedProductType != selectedProductType) {
            this.selectedProductType = selectedProductType;
            getParent().notify(this, NotifyType.CHANGE);
        }
    }

    public void refresh() {
        productTypes.clear();
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        productTypes.add(new ExampleProductType());
        productTypes.add(new ProductType("666666", "Wolrd Dominator Type B"));
        handler.setProductTypes(productTypes);

        handler.clearSelection();
    }
}

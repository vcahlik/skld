package cz.cvut.fit.project.skld.application.db;

import cz.cvut.fit.project.skld.application.core.ProductPosition;

import java.util.List;

/**
 * Implementuje zjistovani pozic produktu.
 * Tato trida neobsahuje metody pro tvoreni novych pozic nebo pokrocilych queries jako v ostatnich tridach v tomto balicku, protoze ProductPositions nejsou reprezentovany na DB urovni.
 */
public interface PositionDAO {
    /**
     * Vraci pozice produktu se zadanym ID.
     * @param productId ID produktu
     * @return Seznam pozic, na kterych je produkt umisten
     */
    List<ProductPosition> findForProductId(long productId);
}

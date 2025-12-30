package org.vaadin.example;

import br.maestro.pizza.model.Store;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.vaadin.crudui.crud.CrudOperation;
import org.vaadin.crudui.crud.impl.GridCrud;

import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route("crud")
public class CRUDView extends VerticalLayout {


    public CRUDView() {
        GridCrud<Store> crud = new GridCrud<>(Store.class);

        crud.getGrid().setColumns("id", "name", "code");

        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.READ, "id", "name", "code");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.ADD, "name", "code");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.UPDATE, "id","name", "code");
        crud.getCrudFormFactory().setVisibleProperties(CrudOperation.DELETE, "id", "name", "code");

        crud.getCrudFormFactory().setDisabledProperties("id");

        crud.setFindAllOperation(this::listAll);
        crud.setAddOperation(this::createStore);
        crud.setDeleteOperation(this::deleteStore);
        crud.setUpdateOperation(this::updateStore);
        add(crud);
    }

    public List<Store> listAll() {
        return Store.listAll();
    }

    @Transactional
    public Store createStore(Store store) {
        store.persist();
        return store;
    }

    @Transactional
    public Store updateStore(Store store) {
        return Store.getEntityManager().merge(store);
    }

    @Transactional
    public void deleteStore(Store store){
        Store.delete("id", store.id);
    }

}

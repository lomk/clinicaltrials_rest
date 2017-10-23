package ua.com.clinicaltrials.services;

import ua.com.clinicaltrials.domain.Menu;

/**
 * Created by Igor on 29-Sep-16.
 */
public interface MenuService {
    Iterable<Menu> listAllMenues();
    Menu getMenuById(Integer id);
    void deleteMenu(Integer id);
    void save(Menu Menu);
    public Menu findByNameEn(String name);
    public Menu findByNameRu(String name);
    public Menu findByNameUa(String name);
}

package ua.com.clinicaltrials.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.clinicaltrials.domain.Menu;
import ua.com.clinicaltrials.repositories.MenuRepository;

/**
 * Created by Igor on 29-Sep-16.
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Iterable<Menu> listAllMenues() {
        return menuRepository.findAllByOrderByIdAsc();
    }

    @Override
    public Menu getMenuById(Integer id) {
        return menuRepository.findOne(id);
    }

    @Override
    public void deleteMenu(Integer id) {
        menuRepository.delete(id);
    }

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    @Override
    public Menu findByNameEn(String name) {
        return null;
    }

    @Override
    public Menu findByNameRu(String name) {
        return null;
    }

    @Override
    public Menu findByNameUa(String name) {
        return null;
    }


}

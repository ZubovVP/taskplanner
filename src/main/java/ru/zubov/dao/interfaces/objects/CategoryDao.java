package ru.zubov.dao.interfaces.objects;

import ru.zubov.dao.interfaces.CrudDao;
import ru.zubov.dao.interfaces.FindDao;
import ru.zubov.entity.Category;

public interface CategoryDao extends CrudDao<Category>, FindDao<Category> {
}

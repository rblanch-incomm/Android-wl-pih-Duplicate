package com.incomm.payithere.views.billerCategory;

import com.contentful.vault.Asset;
import com.incomm.payithere.models.BillerCategoryItem;
import com.incomm.payithere.models.cms.BillerCategoryIcon;
import com.incomm.payithere.models.services.response.BillerCategory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by agodambe on 10/26/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class BillerCategoryPresenterTest {

    @Mock
    BillerCategoryMVP.View view;
    @Mock
    BillerCategoryMVP.Repository repository;
    BillerCategoryPresenter presenter;

    @Before
    public void setUp() throws Exception {
        presenter = new BillerCategoryPresenter(view,repository);
    }

   /* @Test
    public void passCategoryListToView() throws Exception {
        Mockito.when(repository.getCategoryIcons()).thenReturn(getBillerIconsFromCms());
        presenter.OnCategoryListCallSuccess(getCategories());
        Mockito.verify(view).displayCategoryList(Arrays.asList(new BillerCategoryItem(),new BillerCategoryItem()));
    }*/

    @Test
    public void onCategoryListCallError() throws Exception {
        presenter.OnCategoryListCallError("30303");
        Mockito.verify(view).showError("30303");

    }

    @Test public void shouldPassTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn("Title");
        presenter.getTitle();
        Mockito.verify(view).displayTitle("Title");
    }

    @Test public void shouldPassEmptyStringIfNullTitleToView(){
        Mockito.when(repository.getTitle()).thenReturn(null);
        presenter.getTitle();
        Mockito.verify(view).displayTitle("");
    }

    /*public List<BillerCategoryIcon> getBillerIconsFromCms(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("","");
        ArrayList<BillerCategoryIcon> iconList = new ArrayList<>();
        BillerCategoryIcon item = new BillerCategoryIcon();
        item.setCategoryId("TOP");
        item.setIcon(image);
        iconList.add(item);
        return iconList;
    }

    public List<BillerCategory> getCategories(){
        ArrayList<BillerCategory> categoryList = new ArrayList<>();
        BillerCategory item = new BillerCategory();
        item.setId("TOP");
        item.setName("billers");
        categoryList.add(item);
        return categoryList;
    }*/

}
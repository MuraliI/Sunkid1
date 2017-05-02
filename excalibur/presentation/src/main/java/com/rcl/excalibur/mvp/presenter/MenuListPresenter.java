package com.rcl.excalibur.mvp.presenter;

import com.rcl.excalibur.activity.BaseActivity;
import com.rcl.excalibur.domain.Product;
import com.rcl.excalibur.domain.interactor.GetProductDbUseCase;
import com.rcl.excalibur.mvp.view.MenuListView;

import java.util.ArrayList;
import java.util.List;

public class MenuListPresenter {

    private MenuListView view;
    private GetProductDbUseCase productDbUseCase;
    private Product product;

    public MenuListPresenter(MenuListView view, GetProductDbUseCase productDbUseCase) {
        this.view = view;
        this.productDbUseCase = productDbUseCase;
    }

    //TODO: Information from DB
    public void init(String idTypeMenu) {
        final BaseActivity activity = view.getActivity();
        if (activity == null) {
            return;
        }

        view.init();
        List<Menu> menus = getDummiesMenus(idTypeMenu);
        //product = productDbUseCase.get(idTypeMenu);
        if (menus == null) {
            view.showToastAndFinishActivity("Discover Item Not Found");
            return;
        }
        view.addAll(menus);
    }

    private List<Menu> getDummiesMenus(String idTypeMenu) {

        List<Menu> menuList = new ArrayList<>();

        Menu menu1 = new Menu();
        menu1.setTitle("Pancakes");
        menu1.setDescription("Description of this food goes here and it's so delicious that you'll want to eat it whole!");
        menuList.add(menu1);

        Menu menu2 = new Menu();
        menu2.setTitle("Classic Ceasar");
        menu2.setDescription("Description of this food goes here and it's so delicious that you'll want to eat it whole!");
        menuList.add(menu2);

        Menu menu3 = new Menu();
        menu3.setTitle("Rissoto");
        menu3.setDescription("Description of this food goes here and it's so delicious that you'll want to eat it whole!");
        menuList.add(menu3);

        Menu menu4 = new Menu();
        menu4.setTitle("Burger");
        menu4.setDescription("Description of this food goes here and it's so delicious that you'll want to eat it whole!");
        menuList.add(menu4);

        return menuList;
    }

    public class Menu {

        public String title;
        public String description;

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}

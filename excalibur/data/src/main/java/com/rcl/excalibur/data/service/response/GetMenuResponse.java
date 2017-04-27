
package com.rcl.excalibur.data.service.response;

import java.util.List;


public class GetMenuResponse extends BaseResponse {

    private List<MenuResponse> menu;

    public List<MenuResponse> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuResponse> menu) {
        this.menu = menu;
    }

}

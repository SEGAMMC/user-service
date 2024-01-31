package ru.segam.user_service.util;

import java.util.List;
import org.springframework.data.domain.PageImpl;

public class PageImplCustom extends PageImpl {

    public PageImplCustom(List content) {
        super(content);
    }

    @Override
    public int getTotalPages() {
        return super.getTotalPages();
    }

    @Override
    public int getSize() {
        return super.getSize();
    }
}

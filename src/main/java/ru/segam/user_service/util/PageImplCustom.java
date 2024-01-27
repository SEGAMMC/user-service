package ru.segam.user_service.util;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.segam.user_service.entity.User;


public class PageImplCustom extends PageImpl {
    public PageImplCustom(List content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public PageImplCustom(List content) {
        super(content);
    }

    @Override
    public String toString() {
        String contentType = "UNKNOWN";
        List<User> content = getContent();
//        return String.format("Page %s of %d containing %s instances",
//        getNumber() + 1, getTotalPages(), contentType);
        if (!content.isEmpty() && content.get(0) != null) {
            contentType = content.get(0).getClass().getName();
        }
        return "zzzzzzzz";
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

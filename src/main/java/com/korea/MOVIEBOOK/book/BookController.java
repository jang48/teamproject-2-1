package com.korea.MOVIEBOOK.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @GetMapping("/mainPage")
    public String mainPage() {
        return "book/bookMainPage";
    }

    @GetMapping("/list")
    public String getAPI(Model model) {
        List<Book> bestSellerList = bookService.getBestSellerList();
        if (bestSellerList.isEmpty()) {
            bookService.addBestSeller();
            bestSellerList = bookService.getBestSellerList();
        }
        List<List<Book>> bestSellerListList = new ArrayList<>();
        int startIndex = 0;
        int endIndex = 5;
        for (int i = 1; i <= bestSellerList.size()/5; i++) {
            bestSellerListList.add(bestSellerList.subList(startIndex, Math.min(endIndex, bestSellerList.size())));
            startIndex+=5;
            endIndex+=5;
        }
        System.out.println(bestSellerListList.get(0).get(0).getTitle());
        model.addAttribute("bestSellerListList", bestSellerListList);
        return "book/bookList";
    }

    @GetMapping("/detail")
    @ResponseBody
    public String detailPage() {

        return "안녕하세요";
    }

}

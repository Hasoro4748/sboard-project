package com.sboard.controller;

import com.sboard.config.AppInfo;
import com.sboard.dto.ArticleDTO;
import com.sboard.dto.FileDTO;
import com.sboard.dto.PageRequestDTO;
import com.sboard.dto.PageResponseDTO;
import com.sboard.service.ArticleService;
import com.sboard.service.FileService;
import jakarta.mail.Multipart;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;
    private final FileService fileService;

    @GetMapping("/article/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {

            PageResponseDTO pageResponseDTO = articleService.selectArticleAll(pageRequestDTO);
            model.addAttribute(pageResponseDTO);


        return "/article/list";
    }

    @GetMapping("/article/write")
    public String write(){
        return "/article/write";
    }

    @PostMapping("/article/write")
    public String write(ArticleDTO articleDTO, HttpServletRequest req){
        String regip = req.getRemoteAddr();
        articleDTO.setRegip(regip);
        log.info(articleDTO.toString());
        List<MultipartFile> files = articleDTO.getFiles();
        for(MultipartFile file : files){
            log.info(file.getOriginalFilename());
            log.info("isEmpty : " +file.isEmpty());
            log.info("isExist : " + file.getResource().exists());
        }


        // 파일 업로드
        List<FileDTO> uploadedFiles = fileService.uploadFile(articleDTO);

        // 글 저장
        articleDTO.setFile(uploadedFiles.size()); // 첨부 파일 갯수 초기화
        int ano = articleService.insertArticle(articleDTO);

        // 파일 저장
        for(FileDTO fileDTO : uploadedFiles){
            fileDTO.setAno(ano);
            fileService.insertFile(fileDTO);
        }

        return "redirect:/article/list";
    }


    @GetMapping("/article/view")
    public String view(int no, Model model){
        log.info(no+"");
        ArticleDTO articleDTO = articleService.selectArticle(no);



        log.info(articleDTO.toString());


        model.addAttribute(articleDTO);

        return "/article/view";
    }

    @GetMapping("/article/modify")
    public String modify(){
        return "/article/modify";
    }


}

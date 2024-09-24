package com.sboard.dto;

import com.sboard.entity.Article;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    private int no;
    private String title;
    private String content;
    private int comment;

    private List<MultipartFile> files;

    private int file;
    private int hit;
    private String writer;
    private String regip;
    private LocalDateTime rdate;

    // Entity 변환 메서드 대신 ModelMapper사용

    /*
    public Article toEntity(){
        return Article.builder()
                .no(no)
                .cate(cate)
                .title(title)
                .content(content)
                .comment(comment)
                .file(file)
                .hit(hit)
                .writer(writer)
                .regip(regip)
                .rdate(rdate)
                .build();
    }
    */
}

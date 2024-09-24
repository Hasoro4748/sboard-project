package com.sboard.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    @Builder.Default
    private String cate = "free";
    private String title;
    private String content;
    @Builder.Default
    private int comment = 0;
    @Builder.Default
    private int file = 0;
    @Builder.Default
    private int hit = 0;
    private String writer;
    private String regip;
    @CreationTimestamp
    private LocalDateTime rdate;

    //DTO변환 메서드 대신 ModelMapper사용

    /*
    public ArticleDTO toDTO(){
        return ArticleDTO.builder()
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

package com.sboard.dto;

import com.sboard.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {

    private int no;
    private String parent;
    private String content;
    private String writer;
    private String regip;
    
    private String rdate;

    private UserDTO user;


}

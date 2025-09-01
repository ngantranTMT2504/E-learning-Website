package com.course.mapper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.course.dto.request.CommentRequestDTO;
import com.course.dto.response.CommentResponseDTO;
import com.course.model.Account;
import com.course.model.Comment;
import com.course.model.Lesson;

public class CommentMapper {
    public static CommentResponseDTO toResponse(Comment comment) {
        return CommentResponseDTO.builder()
                .id(comment.getId())
                .comment(comment.getComment())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .countLike(comment.getCountLike())
                .countDislike(comment.getCountDislike())
                .createdDate(comment.getCreatedDate())
                .accountId(comment.getAccount() != null ? comment.getAccount().getId() : null)
                .email(comment.getAccount() != null ? comment.getAccount().getEmail() : null)
                .accountAvarta(comment.getAccount() != null ? comment.getAccount().getAvatar() : null)
                .lessonId(comment.getLesson() != null ? comment.getLesson().getId() : null)
                .children(comment.getChildren() != null
                        ? comment.getChildren().stream()
                                .map(CommentMapper::toResponse)
                                .toList()
                        : List.of())
                .build();
    }

    public static Comment toEntity(CommentRequestDTO commentRequestDTO) {
        Account account = new Account();
        account.setId(commentRequestDTO.getAccountId());

        Lesson lesson = new Lesson();
        lesson.setId(commentRequestDTO.getLessonId());

        Comment comment = new Comment();
        comment.setCreatedDate(new Date());
        comment.setAccount(account);
        comment.setChildren(new ArrayList<>());
        comment.setComment(commentRequestDTO.getComment());
        comment.setCountDislike(commentRequestDTO.getCountDislike());
        comment.setLesson(lesson);
        comment.setCountLike(commentRequestDTO.getCountLike());
        if (commentRequestDTO.getParentId() != null) {
            Comment parent = new Comment();
            parent.setId(commentRequestDTO.getParentId());
            comment.setParent(parent);
        }
        return comment;
    }
}

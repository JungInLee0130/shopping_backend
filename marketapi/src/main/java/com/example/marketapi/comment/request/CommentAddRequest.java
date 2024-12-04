package com.example.marketapi.comment.request;

import jakarta.validation.constraints.NotBlank;

public record CommentAddRequest (@NotBlank String spaceUUID,
                                 @NotBlank String directoryUUID,
                                 @NotBlank String content,
                                 @NotBlank String nickname){
}

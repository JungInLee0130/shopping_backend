package com.example.marketapi.comment.request;

import jakarta.validation.constraints.NotBlank;

public record CommentNotifyRequest(@NotBlank Long publisherId,
                                   @NotBlank String publisherUUID,
                                   @NotBlank String directoryUUID) {
}

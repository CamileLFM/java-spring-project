
package com.ifgram.api.dto;

import jakarta.validation.constraints.NotBlank;

public class PostDtos {
    public static class CreatePostRequest {
        @NotBlank public String titulo;
        @NotBlank public String conteudo;
    }
    public static class UpdatePostRequest {
        public String titulo;
        public String conteudo;
    }
}

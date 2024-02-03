package com.lenhatthanh.blog.modules.article.domain.service;

import com.lenhatthanh.blog.modules.article.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.Article;
import com.lenhatthanh.blog.modules.article.domain.exception.ArticleNotFoundException;
import com.lenhatthanh.blog.modules.article.domain.repository.ArticleRepository;
import com.lenhatthanh.blog.modules.article.dto.CommentDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepositoryInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AddCommentServiceImpl implements AddCommentService {
    private ArticleRepository articleRepository;
    private UserRepositoryInterface userRepository;

    @Override
    public void add(String articleId, CommentDto commentDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Article` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Article article = getArticleOrError(articleId);
        article.addComment(commentDto.getContent(), user.get().getId().toString()); //
        articleRepository.save(article);











        /**
         * INCONSISTENCY KHI Có nhiều request đồng thời
         * Ví dụ với business logic: Tối đa 100 comments cho một article
         */
        // Nếu không dùng aggregate ở đây và không dùng cơ chế locking database ở đây
        // Có thể xảy ra một số lỗi của developer như sau:


        // Get article và comments của nó lên:
//        Article article = getArticleOrError(articleId);

        // Kiểm tra số lượng comments của article:
        // ĐÔI KHI DEVELOPER CÓ THỂ QUÊN LOGIC NAY HOẶC VIẾT SAI.
        // Aggregate sẽ giúp developer không quên logic này. (toàn vẹn dữ liệu)
//        if (article.getComments().size() >= 100) {
//            throw new ArticleCommentLimitException();
//        }
        // ....
        // và nhiều business logic khác ở đây

        // Thêm comment vào article:
//        article.addComment(commentDto.getContent(), user.get().getId().toString());

        // Lưu article:
//        articleRepository.save(article); // Lưu xuống DB

//         Với cách làm như trên, có thể xảy ra lỗi sau:
//         Giả sử có 2 request đồng thời:
//         Request 1: Lấy article lên và kiểm tra số lượng comments của article
//         Request 2: Lấy article lên và kiểm tra số lượng comments của article
//         Request 1: Thêm comment vào article
//         Request 2: Thêm comment vào article
//         Request 1: Lưu article
//         Request 2: Lưu article
//         Vậy là số lượng comments của article sẽ là 101 comments, vượt quá giới hạn 100 comments của article
//         Vậy là có thể xảy ra lỗi khi có nhiều request đồng thời
    }

    private Article getArticleOrError(String articleId) {
        Optional<Article> article = articleRepository.findById(articleId);
        if (article.isEmpty()) {
            throw new ArticleNotFoundException();
        }

        return article.get();
    }
}

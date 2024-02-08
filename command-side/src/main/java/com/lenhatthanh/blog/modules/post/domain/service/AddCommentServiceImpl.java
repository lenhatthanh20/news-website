package com.lenhatthanh.blog.modules.post.domain.service;

import com.lenhatthanh.blog.modules.post.application.exception.UserNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.Post;
import com.lenhatthanh.blog.modules.post.domain.exception.PostNotFoundException;
import com.lenhatthanh.blog.modules.post.domain.repository.PostRepository;
import com.lenhatthanh.blog.modules.post.dto.CommentDto;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class AddCommentServiceImpl implements AddCommentService {
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public void add(String postId, CommentDto commentDto) {
        // In the microservice architecture,
        // We have `User` bounded context and `Post` bounded context.
        // That means we have two microservices for each bounded context.
        // So we can use Rest API (non-blocking) to get user information from `User` bounded context.
        Optional<User> user = userRepository.findById(commentDto.getUserId());
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }

        Post post = getPostOrError(postId);
//        post.addComment(commentDto.getContent(), user.get().getId().toString()); //
        postRepository.save(post);











        /**
         * INCONSISTENCY KHI Có nhiều request đồng thời
         * Ví dụ với business logic: Tối đa 100 comments cho một post
         */
        // Nếu không dùng aggregate ở đây và không dùng cơ chế locking database ở đây
        // Có thể xảy ra một số lỗi của developer như sau:


        // Get post và comments của nó lên:
//        Post post = getPostOrError(postId);

        // Kiểm tra số lượng comments của post:
        // ĐÔI KHI DEVELOPER CÓ THỂ QUÊN LOGIC NAY HOẶC VIẾT SAI.
        // Aggregate sẽ giúp developer không quên logic này. (toàn vẹn dữ liệu)
//        if (post.getComments().size() >= 100) {
//            throw new PostCommentLimitException();
//        }
        // ....
        // và nhiều business logic khác ở đây

        // Thêm comment vào post:
//        post.addComment(commentDto.getContent(), user.get().getId().toString());

        // Lưu post:
//        postRepository.save(post); // Lưu xuống DB

//         Với cách làm như trên, có thể xảy ra lỗi sau:
//         Giả sử có 2 request đồng thời:
//         Request 1: Lấy post lên và kiểm tra số lượng comments của post
//         Request 2: Lấy post lên và kiểm tra số lượng comments của post
//         Request 1: Thêm comment vào post
//         Request 2: Thêm comment vào post
//         Request 1: Lưu post
//         Request 2: Lưu post
//         Vậy là số lượng comments của post sẽ là 101 comments, vượt quá giới hạn 100 comments của post
//         Vậy là có thể xảy ra lỗi khi có nhiều request đồng thời
    }

    private Post getPostOrError(String postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException();
        }

        return post.get();
    }
}

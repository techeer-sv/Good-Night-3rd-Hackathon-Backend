package com.example.goodnight3rdhackathonbackend.repository;

import com.example.goodnight3rdhackathonbackend.domain.Comment;
import com.example.goodnight3rdhackathonbackend.domain.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class MemoryCommentRepository implements CommentRepository {
    private static Map<Long, Comment> memory = new ConcurrentHashMap<>();
    private AtomicLong index = new AtomicLong();

    @Override
    public void save(Comment comment) {
        comment.setCommentId(index.incrementAndGet());
        memory.put(index.get(), comment);
    }

    @Override
    public Comment findById(Long id) {
        return memory.get(id);
    }


    @Override
    public List<Comment> findAll(Pageable pageable) {
        return memory.values().stream()
                .sorted(Comparator.comparing(Comment::getCommentId).reversed())
                .skip((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
    }

    @Override
    public void updateById(Long id, Comment comment) {
        memory.put(id, comment);
    }
}

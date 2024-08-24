from django.db import models

class Wish(models.Model):
    title = models.CharField(max_length=100)
    content = models.TextField()
    CATEGORY_CHOICES = [
        ('career', '진로'),
        ('health', '건강'),
        ('relationship', '인간 관계'),
        ('money', '돈'),
        ('goal', '목표'),
        ('study', '학업/성적'),
        ('etc', '기타'),
    ]
    category = models.CharField(max_length=20, choices=CATEGORY_CHOICES)
    created_at = models.DateTimeField(auto_now_add=True)

    PENDING = 'pending'
    APPROVED = 'approved'
    REJECTED = 'rejected'

    CONFIRM_STATUS_CHOICES = [
        (PENDING, '보류됨'),
        (APPROVED, '승인됨'),
        (REJECTED, '거절됨'),
    ]

    is_confirm = models.CharField(
        max_length=10,
        choices=CONFIRM_STATUS_CHOICES,
        default=PENDING
    )
    
    deleted_at = models.DateTimeField(null=True, blank=True)

    def __str__(self):
        return self.title
    


class Comment(models.Model):
    # 댓글이 달린 소원 (ForeignKey로 연결)
    wish = models.ForeignKey(Wish, on_delete=models.CASCADE, related_name='comments')
    
    # 댓글 내용
    content = models.TextField()
    
    # 댓글 등록일
    created_at = models.DateTimeField(auto_now_add=True)
    
    # 댓글 소프트 삭제 여부
    deleted_at = models.DateTimeField(null=True, blank=True)

    def __str__(self):
        return self.content[:20]  # 댓글 내용의 첫 20자를 반환
package com.sparta.memo.controller;

import com.sparta.memo.domain.Memo;
import com.sparta.memo.domain.MemoRepository;
import com.sparta.memo.domain.MemoRequestDto;
import com.sparta.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoService memoService;
    private final MemoRepository memoRepository;

    // 메모 생성 Create
    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    // 메모 조회 Read
    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        //return memoRepository.findAllByOrderByModifiedAtDesc();

        return memoRepository.findAllByModifiedAtBetweenOrderByModifiedAtDesc(LocalDateTime.now(), LocalDateTime.now().minusDays(1));
    }

    // 메모 삭제 Delete
    @DeleteMapping("/api/memos/{id}")
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }

    @PutMapping("/api/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {

        return memoService.update(id, requestDto);
    }
}

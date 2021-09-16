package com.sparta.memo.service;

import com.sparta.memo.domain.Memo;
import com.sparta.memo.domain.MemoRepository;
import com.sparta.memo.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        memo.update(requestDto);

        return memo.getId();
    }
}

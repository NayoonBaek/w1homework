package com.sparta.w1homework.controller;


import com.sparta.w1homework.dto.BoardRequestDto;
import com.sparta.w1homework.entity.Board;
import com.sparta.w1homework.repository.BoardRepository;
import com.sparta.w1homework.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class BoardController {

    private final BoardRepository boardRepository;
    private final BoardService boardService;

    @PostMapping("/api/boards")
    public Board createBoard(@RequestBody BoardRequestDto requestDto) {
        Board board = new Board(requestDto);
        return boardRepository.save(board);
    }

    @GetMapping("/api/boards")
    public List<Board> getBoard() {
        return boardRepository.findAllByOrderByModifiedAtDesc();
    }

    @GetMapping("/api/boards/{id}")
    public Optional<Board> findById(@PathVariable Long id){
        return boardRepository.findById(id);
    }

    @DeleteMapping("/api/boards/{id}")
    public String deleteBoard(@PathVariable Long id) {
        boardRepository.deleteById(id);
        return "삭제완료";
    }
    @PutMapping("/api/boards/{id}")
    public String updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boardService.update(id, requestDto);
        return "수정완료";
    }

    @PostMapping("/api/boards/{id}")
    public boolean checkPass(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        boolean result = boardService.checkPass(id,requestDto.getPassword());
        return result;
    }

}
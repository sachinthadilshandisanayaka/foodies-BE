package com.example.demo.controller;

import com.example.demo.dto.content.ContentReqDTO;
import com.example.demo.dto.content.ContentResDTO;
import com.example.demo.service.ContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mn/cnt")
public class ContentController {
    private ObjectMapper mapper = new ObjectMapper();
    private final ContentService contentService;

    @Autowired
    ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    /**
     * @param type 'P' post, 'E' event
     * @param loginUserId Login user id. It is required
     * @ApiNote
     */
    @GetMapping(path = "/paginated_search")
    public ResponseEntity<List<ContentResDTO>> getContent(@RequestParam(required = false) Long contentID,
                                                          @RequestParam(required = false) Long userId,
                                                          @RequestParam(required = false) String type,
                                                          @RequestParam Long loginUserId,
                                                          @RequestParam(name = "page") int page,
                                                          @RequestParam(name = "size") int size) throws Exception {
        return ResponseEntity.ok().body(contentService.paginatedSearch(contentID, userId, loginUserId, type, page, size));
    }

    @PostMapping(path = "/save")
    public long saveContent(@RequestBody String dtoToJsonString) throws Exception {
        ContentReqDTO dto = mapper.readValue(dtoToJsonString, ContentReqDTO.class);
        return contentService.saveContent(dto);
    }

    @PutMapping(path = "/{contentID}/update")
    public long updateContent(@PathVariable String contentID,
                              @RequestBody String dtoToJsonString) throws Exception {
        ContentReqDTO dto = mapper.readValue(dtoToJsonString, ContentReqDTO.class);
        return contentService.updateContent(dto, contentID);
    }

    @DeleteMapping(path = "/{contentID}/delete")
    public String deleteContent(@PathVariable String contentID) throws Exception {
        return contentService.deleteContent(contentID);
    }
}

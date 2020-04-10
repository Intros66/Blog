package com.ssp.web.admin;

import com.ssp.pojo.Tag;
import com.ssp.service.TagService;
import com.ssp.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    //跳转列表页
    @GetMapping("/tags")
    public String Tags(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                  Pageable pageable,Model model){
        model.addAttribute("page",tagService.listTag(pageable));
        return "admin/tags";
    }

    //跳转新增页
    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tags-input";
    }

    //跳转修改页
    @GetMapping("/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tags-input";
    }

    //新增
    @PostMapping("/tags")
    public String post(@Valid Tag Tag, BindingResult result, RedirectAttributes attributes){

       /* Tag Tag1 = TagService.getTagByName(Tag.getName());
        System.out.println("新增Tag1=======>"+Tag1.toString());
        if (Tag1 != null){
            attributes.addFlashAttribute("message","分类已存在");
            return "admin/Tags-input";
        }*/

        if (result.hasErrors()){
            return "admin/Tags-input";
        }

        Tag Tag2 = tagService.saveTag(Tag);
        if (Tag2 == null){
            attributes.addFlashAttribute("message","新增失败，请重试");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tags";
    }

    //修改
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag Tag, BindingResult result,@PathVariable Long id, RedirectAttributes attributes,Model model){
      /*  System.out.println(Tag.getName());
        Tag Tag1 = TagService.getTagByName(Tag.getName());
        System.out.println("新增Tag1=======>"+Tag1.toString());
        if (Tag1 != null){
            attributes.addFlashAttribute("message","分类已存在");
            return "admin/Tags-input";
        }*/

        if (result.hasErrors()){
            return "admin/Tags-input";
        }

        Tag Tag2 = tagService.updateTag(id,Tag);
        if (Tag2 == null){
            attributes.addFlashAttribute("message","更新失败，请重试");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}

package com.ssp.web.admin;

import com.ssp.pojo.Type;
import com.ssp.service.TypeService;
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
public class TypeController {

    @Autowired
    private TypeService typeService;

    //跳转列表页
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5,sort = {"id"},direction = Sort.Direction.DESC)
                  Pageable pageable,Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "admin/types";
    }

    //跳转新增页
    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/types-input";
    }

    //跳转修改页
    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.getType(id));
        return "admin/types-input";
    }

    //新增
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes){

       /* Type type1 = typeService.getTypeByName(type.getName());
        System.out.println("新增type1=======>"+type1.toString());
        if (type1 != null){
            attributes.addFlashAttribute("message","分类已存在");
            return "admin/types-input";
        }*/

        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type type2 = typeService.saveType(type);
        if (type2 == null){
            attributes.addFlashAttribute("message","新增失败，请重试");
        }else {
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/types";
    }

    //修改
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result,@PathVariable Long id, RedirectAttributes attributes,Model model){
      /*  System.out.println(type.getName());
        Type type1 = typeService.getTypeByName(type.getName());
        System.out.println("新增type1=======>"+type1.toString());
        if (type1 != null){
            attributes.addFlashAttribute("message","分类已存在");
            return "admin/types-input";
        }*/

        if (result.hasErrors()){
            return "admin/types-input";
        }

        Type type2 = typeService.updateType(id,type);
        if (type2 == null){
            attributes.addFlashAttribute("message","更新失败，请重试");
        }else {
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/types";
    }

    //删除
    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/types";
    }

}

package unicorn.mp.api.controller;

import unicorn.mp.api.manager.LocationManager;
import unicorn.mp.api.model.response.CityResponse;
import unicorn.mp.common.enumation.ResponseUtil;
import unicorn.mp.common.utils.ResultWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/unicorn/v1/location")
public class LocationController {

    @Resource
    private LocationManager locationManager;

    @ApiOperation("查询城市信息")
    @GetMapping("/geo")
    public ResultWrapper<CityResponse> citySearch(@RequestParam(required = true) String city,
                                                  @RequestParam String upperCity) {

        return ResponseUtil.success(locationManager.citySearch(city, upperCity));

    }
}

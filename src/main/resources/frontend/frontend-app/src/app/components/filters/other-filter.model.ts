export class OtherFilters {
    public parkingFilter: boolean;
    public restaurantFilter: boolean;
    public petsAllowedFilter: boolean;
    public nonsmokingRoomsFilter: boolean;
    public swimmingPoolFilter: boolean;
    public beachfrontFilter: boolean;
    public airConditioningFilter: boolean;
    public freeWifiFilter: boolean;
    public saunaFilter: boolean;
    public fitnessFilter: boolean;
      
    constructor(parkingFilter?: boolean,
                restaurantFilter?: boolean,
                petsAllowedFilter?: boolean,
                nonsmokingRoomsFilter?: boolean,
                swimmingPoolFilter?: boolean,
                beachfrontFilter?: boolean,
                airConditioningFilter?: boolean,
                freeWifiFilter?: boolean,
                saunaFilter?: boolean,
                fitnessFilter?: boolean) {
        this.parkingFilter = parkingFilter;
        this.restaurantFilter = restaurantFilter;
        this.petsAllowedFilter = petsAllowedFilter;
        this.nonsmokingRoomsFilter = nonsmokingRoomsFilter;
        this.swimmingPoolFilter = swimmingPoolFilter;
        this.beachfrontFilter = beachfrontFilter;
        this.airConditioningFilter = airConditioningFilter;
        this.freeWifiFilter = freeWifiFilter;
        this.saunaFilter = saunaFilter;
        this.fitnessFilter = fitnessFilter;
    }
  }
  
import { OtherFilters } from './other-filter.model';

export class FiltersModel {
    public minPrice: number;
    public maxPrice: number;
    public minRating: number;
    public maxRating: number;
    public starsFilter: number;
    public currentlySelectedActivities: string[];
    public currentlySelectedLocations: string[];
    public otherFilters: OtherFilters;
      
    constructor(minPrice: number,
                maxPrice: number,
                minRating: number,
                maxRating: number,
                starsFilter: number,
                currentlySelectedActivities: string[],
                currentlySelectedLocations: string[],
                otherFilters: OtherFilters) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.starsFilter = starsFilter;
        this.currentlySelectedActivities = currentlySelectedActivities;
        this.currentlySelectedLocations = currentlySelectedLocations;
        this.otherFilters = otherFilters;
    }
  }
package constructors.class_problems;

public class BusRoute {
    private static final int DEFAULT_PRIORITY = 1;

    private String routeCode;
    private String routeName;
    private int priority;

    public BusRoute(String routeCode, String routeName, int priority) {
        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, DEFAULT_PRIORITY);
    }

    public String getRouteCode() {
        return routeCode;
    }

    int compareTo(BusRoute other) {
        if (this.priority != other.priority) {
            return other.priority - this.priority;
        }

        int codeComparison = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {
        BusRoute[] result = new BusRoute[routes.length];
        for (int i = 0; i < routes.length; i++) {
            result[i] = routes[i];
        }

        for (int i = 0; i < result.length - 1; i++) {
            for (int j = 0; j < result.length - 1 - i; j++) {
                if (result[j].compareTo(result[j + 1]) > 0) {
                    BusRoute temp = result[j];
                    result[j] = result[j + 1];
                    result[j + 1] = temp;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        BusRoute[] routes = {
                new BusRoute("RT205L", "Airport Express", 3),
                new BusRoute("rt201j", "City Central", 4),
                new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);
        for (int i = 0; i < ranked.length; i++) {
            System.out.println(ranked[i].getRouteCode());
        }
    }
}

if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Triangle-Solver'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Triangle-Solver'.");
}
this['Triangle-Solver'] = function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var ensureNotNull = Kotlin.ensureNotNull;
  var roundToInt = Kotlin.kotlin.math.roundToInt_yrwdxr$;
  var println = Kotlin.kotlin.io.println_s8jyv4$;
  var math = Kotlin.kotlin.math;
  var sum = Kotlin.kotlin.collections.sum_pnorak$;
  var Exception = Kotlin.kotlin.Exception;
  var equals = Kotlin.equals;
  var min = Kotlin.kotlin.collections.min_pnorak$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var joinToString = Kotlin.kotlin.collections.joinToString_cgipc5$;
  var contentEquals = Kotlin.arrayEquals;
  var reversedArray = Kotlin.kotlin.collections.reversedArray_4b5429$;
  var unboxChar = Kotlin.unboxChar;
  TriangleType$Part.prototype = Object.create(Enum.prototype);
  TriangleType$Part.prototype.constructor = TriangleType$Part;
  var screen;
  var ioPane;
  var anglesPane;
  var renderer;
  function main$fitScreen() {
    screen.width = roundToInt(ensureNotNull(screen.parentElement).clientWidth * 0.75);
    screen.height = roundToInt(window.innerHeight * 0.5);
  }
  function main$lambda(closure$fitScreen) {
    return function (it) {
      closure$fitScreen();
      return null;
    };
  }
  function main(args) {
    var fitScreen = main$fitScreen;
    window.onresize = main$lambda(fitScreen);
    fitScreen();
    println(evaluateMath('sqrt(3^2 + 4^2)'));
  }
  function hasBeenInitialized$lambda(a) {
    return a > 0;
  }
  var hasBeenInitialized;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  function getIndicesSuchThat(predicate) {
    var $receiver = [0, 1, 2];
    var destination = ArrayList_init();
    var tmp$;
    for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
      var element = $receiver[tmp$];
      if (predicate(element))
        destination.add_11rb$(element);
    }
    return destination;
  }
  function asDegrees(radians) {
    return radians * 180 / math.PI;
  }
  function asRadians(degrees) {
    return degrees * math.PI / 180;
  }
  function Triangle(sides, angles) {
    this.sides = sides;
    this.angles = angles;
  }
  Triangle.prototype.isValid = function () {
    var tmp$;
    if (this.isSolved) {
      var acceptableError = 0.005;
      var anglesAddToPi = sum(this.angles) < math.PI + acceptableError && sum(this.angles) > math.PI - acceptableError;
      var sidesFulfillLegInequality = this.sides[0] < this.sides[1] + this.sides[2] && this.sides[1] < this.sides[0] + this.sides[2] && this.sides[2] < this.sides[0] + this.sides[1];
      return anglesAddToPi && sidesFulfillLegInequality;
    }
     else {
      try {
        var $receiver = this.solutions();
        var destination = ArrayList_init();
        var tmp$_0;
        for (tmp$_0 = 0; tmp$_0 !== $receiver.length; ++tmp$_0) {
          var element = $receiver[tmp$_0];
          if (!element.isSolved || !element.isValid())
            destination.add_11rb$(element);
        }
        var tmp$_1;
        tmp$_1 = destination.iterator();
        while (tmp$_1.hasNext()) {
          var element_0 = tmp$_1.next();
          return false;
        }
        tmp$ = true;
      }
       catch (e) {
        if (Kotlin.isType(e, Exception)) {
          tmp$ = false;
        }
         else
          throw e;
      }
      return tmp$;
    }
  };
  Object.defineProperty(Triangle.prototype, 'type_0', {
    get: function () {
      return TriangleType_init(this.sides, this.angles);
    }
  });
  Object.defineProperty(Triangle.prototype, 'isSolved', {
    get: function () {
      var $receiver = this.sides;
      var destination = ArrayList_init();
      var tmp$;
      for (tmp$ = 0; tmp$ !== $receiver.length; ++tmp$) {
        var element = $receiver[tmp$];
        if (hasBeenInitialized(element))
          destination.add_11rb$(element);
      }
      var tmp$_0 = destination.size === 3;
      if (tmp$_0) {
        var $receiver_0 = this.angles;
        var destination_0 = ArrayList_init();
        var tmp$_1;
        for (tmp$_1 = 0; tmp$_1 !== $receiver_0.length; ++tmp$_1) {
          var element_0 = $receiver_0[tmp$_1];
          if (hasBeenInitialized(element_0))
            destination_0.add_11rb$(element_0);
        }
        tmp$_0 = destination_0.size === 3;
      }
      return tmp$_0;
    }
  });
  function Triangle$solutions$reSolve(closure$primary, closure$solved) {
    return function () {
      closure$solved.v = closure$primary.v.solutions();
    };
  }
  function Triangle$solutions$lambda(closure$primary) {
    return function (it) {
      return !hasBeenInitialized(closure$primary.v.sides[it]);
    };
  }
  function Triangle$solutions$lambda_0(closure$unknownSideIndex) {
    return function (it) {
      return it !== closure$unknownSideIndex;
    };
  }
  function Triangle$solutions$lambda_1(closure$primary) {
    return function (it) {
      return closure$primary.v.angles[it] === min(closure$primary.v.angles);
    };
  }
  function Triangle$solutions$lambda_2(closure$primary) {
    return function (it) {
      return hasBeenInitialized(closure$primary.v.angles[it]);
    };
  }
  function Triangle$solutions$lambda_3(closure$primary) {
    return function (it) {
      return hasBeenInitialized(closure$primary.v.sides[it]);
    };
  }
  function Triangle$solutions$lambda_4(closure$knownAngleIndices) {
    return function (it) {
      return !closure$knownAngleIndices.contains_11rb$(it);
    };
  }
  function Triangle$solutions$lambda_5(closure$knownSideIndices) {
    return function (it) {
      return !closure$knownSideIndices.contains_11rb$(it);
    };
  }
  function Triangle$solutions$lambda_6(closure$primary) {
    return function (it) {
      return !hasBeenInitialized(closure$primary.v.angles[it]);
    };
  }
  function Triangle$solutions$lambda_7(closure$unknownAngleIndex) {
    return function (it) {
      return it !== closure$unknownAngleIndex;
    };
  }
  function Triangle$solutions$lambda_8(closure$primary) {
    return function (it) {
      return hasBeenInitialized(closure$primary.v.sides[it]);
    };
  }
  function Triangle$solutions$lambda_9(closure$primary) {
    return function (it) {
      return hasBeenInitialized(closure$primary.v.angles[it]);
    };
  }
  var Math_0 = Math;
  Triangle.prototype.solutions = function () {
    var tmp$, tmp$_0;
    var solved = {v: [this.copy_0()]};
    var primary = {v: solved.v[0]};
    var reSolve = Triangle$solutions$reSolve(primary, solved);
    tmp$ = this.type_0;
    if (equals(tmp$, SSS)) {
      var tmp$_1 = primary.v.angles;
      var $receiver = primary.v.sides[1];
      var tmp$_2 = Math_0.pow($receiver, 2.0);
      var $receiver_0 = primary.v.sides[2];
      var tmp$_3 = tmp$_2 + Math_0.pow($receiver_0, 2.0);
      var $receiver_1 = primary.v.sides[0];
      var x = (tmp$_3 - Math_0.pow($receiver_1, 2.0)) / (2 * primary.v.sides[1] * primary.v.sides[2]);
      tmp$_1[0] = Math_0.acos(x);
      var tmp$_4 = primary.v.angles;
      var $receiver_2 = primary.v.sides[0];
      var tmp$_5 = Math_0.pow($receiver_2, 2.0);
      var $receiver_3 = primary.v.sides[2];
      var tmp$_6 = tmp$_5 + Math_0.pow($receiver_3, 2.0);
      var $receiver_4 = primary.v.sides[1];
      var x_0 = (tmp$_6 - Math_0.pow($receiver_4, 2.0)) / (2 * primary.v.sides[0] * primary.v.sides[2]);
      tmp$_4[1] = Math_0.acos(x_0);
      primary.v.angles[2] = math.PI - primary.v.angles[0] - primary.v.angles[1];
    }
     else if (equals(tmp$, SAS)) {
      var unknownSideIndex = getIndicesSuchThat(Triangle$solutions$lambda(primary)).get_za3lpa$(0);
      var unknownAngleIndices = getIndicesSuchThat(Triangle$solutions$lambda_0(unknownSideIndex));
      var tmp$_7 = primary.v.sides;
      var $receiver_5 = solved.v[0].sides[unknownAngleIndices.get_za3lpa$(0)];
      var tmp$_8 = Math_0.pow($receiver_5, 2.0);
      var $receiver_6 = primary.v.sides[unknownAngleIndices.get_za3lpa$(1)];
      var tmp$_9 = tmp$_8 + Math_0.pow($receiver_6, 2.0);
      var tmp$_10 = 2 * primary.v.sides[unknownAngleIndices.get_za3lpa$(0)] * primary.v.sides[unknownAngleIndices.get_za3lpa$(1)];
      var x_1 = primary.v.angles[unknownSideIndex];
      var x_2 = tmp$_9 - tmp$_10 * Math_0.cos(x_1);
      tmp$_7[unknownSideIndex] = Math_0.sqrt(x_2);
      reSolve();
    }
     else if (equals(tmp$, AAA)) {
      var smallestSide = getIndicesSuchThat(Triangle$solutions$lambda_1(primary)).get_za3lpa$(0);
      primary.v.sides[smallestSide] = 1.0;
      reSolve();
    }
     else if (equals(tmp$, ASA)) {
      var knownAngleIndices = getIndicesSuchThat(Triangle$solutions$lambda_2(primary));
      var knownSideIndices = getIndicesSuchThat(Triangle$solutions$lambda_3(primary));
      if (knownAngleIndices.size === 2) {
        primary.v.angles[getIndicesSuchThat(Triangle$solutions$lambda_4(knownAngleIndices)).get_za3lpa$(0)] = math.PI - primary.v.angles[knownAngleIndices.get_za3lpa$(0)] - primary.v.angles[knownAngleIndices.get_za3lpa$(1)];
      }
      tmp$_0 = getIndicesSuchThat(Triangle$solutions$lambda_5(knownSideIndices)).iterator();
      while (tmp$_0.hasNext()) {
        var unknownIndex = tmp$_0.next();
        var tmp$_11 = primary.v.sides;
        var x_3 = primary.v.angles[unknownIndex];
        var tmp$_12 = Math_0.sin(x_3) * primary.v.sides[knownSideIndices.get_za3lpa$(0)];
        var x_4 = primary.v.angles[knownSideIndices.get_za3lpa$(0)];
        tmp$_11[unknownIndex] = tmp$_12 / Math_0.sin(x_4);
      }
    }
     else if (equals(tmp$, AAS)) {
      var unknownAngleIndex = getIndicesSuchThat(Triangle$solutions$lambda_6(primary)).get_za3lpa$(0);
      var knownAngleIndices_0 = getIndicesSuchThat(Triangle$solutions$lambda_7(unknownAngleIndex));
      primary.v.angles[unknownAngleIndex] = math.PI - primary.v.angles[knownAngleIndices_0.get_za3lpa$(0)] - primary.v.angles[knownAngleIndices_0.get_za3lpa$(1)];
      reSolve();
    }
     else if (equals(tmp$, ASS)) {
      var knownSides = getIndicesSuchThat(Triangle$solutions$lambda_8(primary));
      var knownAngle = getIndicesSuchThat(Triangle$solutions$lambda_9(primary)).get_za3lpa$(0);
      var destination = ArrayList_init();
      var tmp$_13;
      tmp$_13 = knownSides.iterator();
      while (tmp$_13.hasNext()) {
        var element = tmp$_13.next();
        if (element !== knownAngle)
          destination.add_11rb$(element);
      }
      var unknownOppositeAngle = destination.get_za3lpa$(0);
      var tmp$_14 = primary.v.sides[unknownOppositeAngle] / primary.v.sides[knownAngle];
      var x_5 = primary.v.angles[knownAngle];
      var ASSType = tmp$_14 * Math_0.sin(x_5);
      if (primary.v.sides[knownAngle] >= primary.v.sides[unknownOppositeAngle]) {
        primary.v.angles[unknownOppositeAngle] = Math_0.asin(ASSType);
        reSolve();
      }
       else {
        primary.v = solved.v[0];
        var secondary = this.copy_0();
        primary.v.angles[unknownOppositeAngle] = Math_0.asin(ASSType);
        secondary.angles[unknownOppositeAngle] = math.PI - Math_0.asin(ASSType);
        solved.v = [primary.v.solutions()[0], secondary.solutions()[0]];
      }
    }
    return solved.v;
  };
  Triangle.prototype.area = function () {
    if (!this.isSolved)
      return 0.0;
    var s = sum(this.sides) / 2;
    var x = s * (s - this.sides[0]) * (s - this.sides[1]) * (s - this.sides[2]);
    return Math_0.sqrt(x);
  };
  Triangle.prototype.copy_0 = function () {
    var clone = Triangle_init();
    clone.sides = this.sides.slice();
    clone.angles = this.angles.slice();
    return clone;
  };
  Triangle.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Triangle',
    interfaces: []
  };
  var Array_0 = Array;
  function Triangle_init($this) {
    $this = $this || Object.create(Triangle.prototype);
    var array = Array_0(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = -1.0;
    }
    var array_0 = Array_0(3);
    var tmp$_0;
    tmp$_0 = array_0.length - 1 | 0;
    for (var i_0 = 0; i_0 <= tmp$_0; i_0++) {
      array_0[i_0] = -1.0;
    }
    Triangle.call($this, array, array_0);
    return $this;
  }
  var SSS;
  var SAS;
  var AAA;
  var ASA;
  var AAS;
  var ASS;
  function TriangleType() {
    var array = Array_0(3);
    var tmp$;
    tmp$ = array.length - 1 | 0;
    for (var i = 0; i <= tmp$; i++) {
      array[i] = TriangleType$Part$UNKNOWN_getInstance();
    }
    this.type = array;
  }
  function TriangleType$Part(name, ordinal) {
    Enum.call(this);
    this.name$ = name;
    this.ordinal$ = ordinal;
  }
  function TriangleType$Part_initFields() {
    TriangleType$Part_initFields = function () {
    };
    TriangleType$Part$SIDE_instance = new TriangleType$Part('SIDE', 0);
    TriangleType$Part$ANGLE_instance = new TriangleType$Part('ANGLE', 1);
    TriangleType$Part$UNKNOWN_instance = new TriangleType$Part('UNKNOWN', 2);
  }
  var TriangleType$Part$SIDE_instance;
  function TriangleType$Part$SIDE_getInstance() {
    TriangleType$Part_initFields();
    return TriangleType$Part$SIDE_instance;
  }
  var TriangleType$Part$ANGLE_instance;
  function TriangleType$Part$ANGLE_getInstance() {
    TriangleType$Part_initFields();
    return TriangleType$Part$ANGLE_instance;
  }
  var TriangleType$Part$UNKNOWN_instance;
  function TriangleType$Part$UNKNOWN_getInstance() {
    TriangleType$Part_initFields();
    return TriangleType$Part$UNKNOWN_instance;
  }
  TriangleType$Part.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'Part',
    interfaces: [Enum]
  };
  function TriangleType$Part$values() {
    return [TriangleType$Part$SIDE_getInstance(), TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$UNKNOWN_getInstance()];
  }
  TriangleType$Part.values = TriangleType$Part$values;
  function TriangleType$Part$valueOf(name) {
    switch (name) {
      case 'SIDE':
        return TriangleType$Part$SIDE_getInstance();
      case 'ANGLE':
        return TriangleType$Part$ANGLE_getInstance();
      case 'UNKNOWN':
        return TriangleType$Part$UNKNOWN_getInstance();
      default:throwISE('No enum constant com.saurabhtotey.trianglesolver.TriangleType.Part.' + name);
    }
  }
  TriangleType$Part.valueOf_61zpoe$ = TriangleType$Part$valueOf;
  function TriangleType$toString$lambda(it) {
    switch (it.name) {
      case 'SIDE':
        return 'S';
      case 'ANGLE':
        return 'A';
      case 'UNKNOWN':
        return '?';
      default:return Kotlin.noWhenBranchMatched();
    }
  }
  TriangleType.prototype.toString = function () {
    return joinToString(this.type, '', void 0, void 0, void 0, void 0, TriangleType$toString$lambda);
  };
  TriangleType.prototype.equals = function (other) {
    return Kotlin.isType(other, TriangleType) && (contentEquals(other.type, this.type) || contentEquals(other.type, reversedArray(this.type)));
  };
  TriangleType.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TriangleType',
    interfaces: []
  };
  function TriangleType_init(sides, angles, $this) {
    $this = $this || Object.create(TriangleType.prototype);
    TriangleType.call($this);
    var tmp$;
    var initializedSides = getIndicesSuchThat(TriangleType_init$lambda(sides));
    var initializedAngles = getIndicesSuchThat(TriangleType_init$lambda_0(angles));
    if ((initializedSides.size + initializedAngles.size | 0) < 3)
      return $this;
    switch (initializedSides.size) {
      case 3:
        tmp$ = [TriangleType$Part$SIDE_getInstance(), TriangleType$Part$SIDE_getInstance(), TriangleType$Part$SIDE_getInstance()];
        break;
      case 1:
        tmp$ = initializedAngles.size === 3 || hasBeenInitialized(sides[getIndicesSuchThat(TriangleType_init$lambda_1(initializedAngles)).get_za3lpa$(0)]) ? [TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$SIDE_getInstance(), TriangleType$Part$ANGLE_getInstance()] : [TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$SIDE_getInstance()];
        break;
      case 2:
        if (hasBeenInitialized(angles[getIndicesSuchThat(TriangleType_init$lambda_2(initializedSides)).get_za3lpa$(0)]) || initializedAngles.size === 3)
          tmp$ = [TriangleType$Part$SIDE_getInstance(), TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$SIDE_getInstance()];
        else if (initializedAngles.size > 1)
          tmp$ = [TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$SIDE_getInstance()];
        else
          tmp$ = [TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$SIDE_getInstance(), TriangleType$Part$SIDE_getInstance()];
        break;
      case 0:
        tmp$ = [TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$ANGLE_getInstance(), TriangleType$Part$ANGLE_getInstance()];
        break;
      default:tmp$ = $this.type;
        break;
    }
    $this.type = tmp$;
    return $this;
  }
  function TriangleType_init$lambda(closure$sides) {
    return function (it) {
      return hasBeenInitialized(closure$sides[it]);
    };
  }
  function TriangleType_init$lambda_0(closure$angles) {
    return function (it) {
      return hasBeenInitialized(closure$angles[it]);
    };
  }
  function TriangleType_init$lambda_1(closure$initializedAngles) {
    return function (it) {
      return !closure$initializedAngles.contains_11rb$(it);
    };
  }
  function TriangleType_init$lambda_2(closure$initializedSides) {
    return function (it) {
      return !closure$initializedSides.contains_11rb$(it);
    };
  }
  var iterator = Kotlin.kotlin.text.iterator_gw00vp$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  function TriangleType_init_0(stringType, $this) {
    $this = $this || Object.create(TriangleType.prototype);
    TriangleType.call($this);
    var destination = ArrayList_init(stringType.length);
    var tmp$;
    tmp$ = iterator(stringType);
    while (tmp$.hasNext()) {
      var item = unboxChar(tmp$.next());
      var tmp$_0 = destination.add_11rb$;
      var it = toBoxedChar(item);
      var transform$result;
      transform$break: do {
        var $receiver = unboxChar(it);
        switch (unboxChar(String.fromCharCode($receiver).toLowerCase().charCodeAt(0))) {
          case 115:
            transform$result = TriangleType$Part$SIDE_getInstance();
            break transform$break;
          case 97:
            transform$result = TriangleType$Part$ANGLE_getInstance();
            break transform$break;
          default:transform$result = TriangleType$Part$UNKNOWN_getInstance();
            break transform$break;
        }
      }
       while (false);
      tmp$_0.call(destination, transform$result);
    }
    $this.type = copyToArray(destination);
    return $this;
  }
  var package$com = _.com || (_.com = {});
  var package$saurabhtotey = package$com.saurabhtotey || (package$com.saurabhtotey = {});
  var package$trianglesolver = package$saurabhtotey.trianglesolver || (package$saurabhtotey.trianglesolver = {});
  Object.defineProperty(package$trianglesolver, 'screen', {
    get: function () {
      return screen;
    }
  });
  Object.defineProperty(package$trianglesolver, 'ioPane', {
    get: function () {
      return ioPane;
    }
  });
  Object.defineProperty(package$trianglesolver, 'anglesPane', {
    get: function () {
      return anglesPane;
    }
  });
  Object.defineProperty(package$trianglesolver, 'renderer', {
    get: function () {
      return renderer;
    }
  });
  package$trianglesolver.main_kand9s$ = main;
  Object.defineProperty(package$trianglesolver, 'hasBeenInitialized', {
    get: function () {
      return hasBeenInitialized;
    }
  });
  package$trianglesolver.getIndicesSuchThat_8xwv17$ = getIndicesSuchThat;
  package$trianglesolver.asDegrees_14dthe$ = asDegrees;
  package$trianglesolver.asRadians_14dthe$ = asRadians;
  package$trianglesolver.Triangle_init = Triangle_init;
  package$trianglesolver.Triangle = Triangle;
  Object.defineProperty(package$trianglesolver, 'SSS', {
    get: function () {
      return SSS;
    }
  });
  Object.defineProperty(package$trianglesolver, 'SAS', {
    get: function () {
      return SAS;
    }
  });
  Object.defineProperty(package$trianglesolver, 'AAA', {
    get: function () {
      return AAA;
    }
  });
  Object.defineProperty(package$trianglesolver, 'ASA', {
    get: function () {
      return ASA;
    }
  });
  Object.defineProperty(package$trianglesolver, 'AAS', {
    get: function () {
      return AAS;
    }
  });
  Object.defineProperty(package$trianglesolver, 'ASS', {
    get: function () {
      return ASS;
    }
  });
  Object.defineProperty(TriangleType$Part, 'SIDE', {
    get: TriangleType$Part$SIDE_getInstance
  });
  Object.defineProperty(TriangleType$Part, 'ANGLE', {
    get: TriangleType$Part$ANGLE_getInstance
  });
  Object.defineProperty(TriangleType$Part, 'UNKNOWN', {
    get: TriangleType$Part$UNKNOWN_getInstance
  });
  TriangleType.Part = TriangleType$Part;
  package$trianglesolver.TriangleType_init_8efa2c$ = TriangleType_init;
  package$trianglesolver.TriangleType_init_61zpoe$ = TriangleType_init_0;
  package$trianglesolver.TriangleType = TriangleType;
  var tmp$, tmp$_0, tmp$_1, tmp$_2;
  screen = Kotlin.isType(tmp$ = document.getElementById('screen'), HTMLCanvasElement) ? tmp$ : throwCCE();
  ioPane = Kotlin.isType(tmp$_0 = document.getElementById('ioPane'), HTMLDivElement) ? tmp$_0 : throwCCE();
  anglesPane = Kotlin.isType(tmp$_1 = document.getElementById('anglesPane'), HTMLDivElement) ? tmp$_1 : throwCCE();
  renderer = Kotlin.isType(tmp$_2 = screen.getContext('2d'), CanvasRenderingContext2D) ? tmp$_2 : throwCCE();
  hasBeenInitialized = hasBeenInitialized$lambda;
  SSS = TriangleType_init_0('SSS');
  SAS = TriangleType_init_0('SAS');
  AAA = TriangleType_init_0('AAA');
  ASA = TriangleType_init_0('ASA');
  AAS = TriangleType_init_0('AAS');
  ASS = TriangleType_init_0('ASS');
  main([]);
  Kotlin.defineModule('Triangle-Solver', _);
  return _;
}(typeof this['Triangle-Solver'] === 'undefined' ? {} : this['Triangle-Solver'], kotlin);

if (typeof kotlin === 'undefined') {
  throw new Error("Error loading module 'Triangle-Solver'. Its dependency 'kotlin' was not found. Please, check whether 'kotlin' is loaded prior to 'Triangle-Solver'.");
}
this['Triangle-Solver'] = function (_, Kotlin) {
  'use strict';
  var throwCCE = Kotlin.throwCCE;
  var asList = Kotlin.org.w3c.dom.asList_kt9thq$;
  var IntRange = Kotlin.kotlin.ranges.IntRange;
  var equals = Kotlin.equals;
  var sliceArray = Kotlin.kotlin.collections.sliceArray_8r7b3e$;
  var Exception = Kotlin.kotlin.Exception;
  var Unit = Kotlin.kotlin.Unit;
  var substring = Kotlin.kotlin.text.substring_fc3b62$;
  var iterator = Kotlin.kotlin.text.iterator_gw00vp$;
  var contains = Kotlin.kotlin.text.contains_sgbm27$;
  var toBoxedChar = Kotlin.toBoxedChar;
  var replace = Kotlin.kotlin.text.replace_680rmw$;
  var unboxChar = Kotlin.unboxChar;
  var ensureNotNull = Kotlin.ensureNotNull;
  var roundToInt = Kotlin.kotlin.math.roundToInt_yrwdxr$;
  var math = Kotlin.kotlin.math;
  var sum = Kotlin.kotlin.collections.sum_pnorak$;
  var min = Kotlin.kotlin.collections.min_pnorak$;
  var Kind_CLASS = Kotlin.Kind.CLASS;
  var Enum = Kotlin.kotlin.Enum;
  var throwISE = Kotlin.throwISE;
  var joinToString = Kotlin.kotlin.collections.joinToString_cgipc5$;
  var contentEquals = Kotlin.arrayEquals;
  var reversedArray = Kotlin.kotlin.collections.reversedArray_4b5429$;
  var toList = Kotlin.kotlin.collections.toList_7wnvza$;
  var numberToInt = Kotlin.numberToInt;
  var until = Kotlin.kotlin.ranges.until_dqglrj$;
  var toDouble = Kotlin.kotlin.text.toDouble_pdl1vz$;
  var endsWith = Kotlin.kotlin.text.endsWith_7epoxm$;
  var toString = Kotlin.toString;
  var toInt = Kotlin.kotlin.text.toInt_pdl1vz$;
  var indexOf = Kotlin.kotlin.text.indexOf_l5u8uk$;
  var toChar = Kotlin.toChar;
  var repeat = Kotlin.kotlin.text.repeat_94bcnn$;
  var to = Kotlin.kotlin.to_ujzrz7$;
  var hashMapOf = Kotlin.kotlin.collections.hashMapOf_qfcya0$;
  TriangleType$Part.prototype = Object.create(Enum.prototype);
  TriangleType$Part.prototype.constructor = TriangleType$Part;
  function main$lambda(closure$degreesBox, closure$radiansBox) {
    return function (it) {
      closure$radiansBox.value = evaluateMath('pi * ' + closure$degreesBox.value + ' / 180').toString();
      return null;
    };
  }
  function main$lambda_0(closure$radiansBox, closure$degreesBox) {
    return function (it) {
      closure$degreesBox.value = evaluateMath('180 * ' + closure$radiansBox.value + ' / pi').toString();
      return null;
    };
  }
  var Array_0 = Array;
  function main$updateTriangles(closure$inputBoxes, closure$componentDropdowns, closure$angleModeSelection, closure$drawer) {
    return function () {
      var tmp$, tmp$_0;
      tmp$_0 = closure$drawer;
      try {
        var array = Array_0(6);
        var tmp$_1;
        tmp$_1 = array.length - 1 | 0;
        for (var i = 0; i <= tmp$_1; i++) {
          array[i] = -1.0;
        }
        var partsArray = array;
        for (var i_0 = 0; i_0 <= 2; i_0++) {
          partsArray[closure$componentDropdowns.get_za3lpa$(i_0).selectedIndex] = evaluateMath(closure$inputBoxes[i_0].value);
        }
        if (equals(closure$angleModeSelection.value, 'Degrees')) {
          for (var i_1 = 3; i_1 <= 5; i_1++) {
            partsArray[i_1] = asRadians(partsArray[i_1]);
          }
        }
        tmp$ = new Triangle(sliceArray(partsArray, new IntRange(0, 2)), sliceArray(partsArray, new IntRange(3, 5)));
      }
       catch (e) {
        if (Kotlin.isType(e, Exception)) {
          tmp$ = null;
        }
         else
          throw e;
      }
      tmp$_0.triangle = tmp$;
    };
  }
  function main$lambda$lambda(closure$updateTriangles) {
    return function (it) {
      closure$updateTriangles();
      return Unit;
    };
  }
  function main$lambda$lambda_0(closure$componentDropdowns, closure$typeIO, closure$updateTriangles) {
    return function (it) {
      var tmp$;
      var array = Array_0(6);
      var tmp$_0;
      tmp$_0 = array.length - 1 | 0;
      for (var i = 0; i <= tmp$_0; i++) {
        array[i] = -1.0;
      }
      var partsArray = array;
      tmp$ = closure$componentDropdowns.iterator();
      while (tmp$.hasNext()) {
        var dropdown = tmp$.next();
        partsArray[dropdown.selectedIndex] = 1.0;
      }
      closure$typeIO.value = TriangleType_init(sliceArray(partsArray, new IntRange(0, 2)), sliceArray(partsArray, new IntRange(3, 5))).toString();
      closure$updateTriangles();
      return null;
    };
  }
  function main$lambda_1(closure$typeIO, closure$componentDropdowns, closure$updateTriangles) {
    return function (it) {
      var tmp$, tmp$_0, tmp$_1;
      if (closure$typeIO.value.length > 3) {
        closure$typeIO.value = substring(closure$typeIO.value, new IntRange(0, 2));
      }
      closure$typeIO.value = closure$typeIO.value.toUpperCase();
      tmp$ = iterator(closure$typeIO.value);
      while (tmp$.hasNext()) {
        var letter = unboxChar(tmp$.next());
        if (!contains('AS', letter)) {
          closure$typeIO.value = replace(closure$typeIO.value, String.fromCharCode(letter), '');
        }
      }
      if (closure$typeIO.value.length === 3) {
        if (closure$typeIO.value.charCodeAt(1) !== closure$typeIO.value.charCodeAt(0) && closure$typeIO.value.charCodeAt(0) === closure$typeIO.value.charCodeAt(2)) {
          for (var i = 0; i <= 2; i++) {
            closure$componentDropdowns.get_za3lpa$(i).selectedIndex = i + (closure$typeIO.value.charCodeAt(i) === 65 ? 3 : 0) | 0;
          }
        }
         else {
          var sideIndex = 0;
          var angleIndex = 3;
          for (var i_0 = 0; i_0 <= 2; i_0++) {
            closure$componentDropdowns.get_za3lpa$(i_0).selectedIndex = closure$typeIO.value.charCodeAt(i_0) === 83 ? (tmp$_0 = sideIndex, sideIndex = tmp$_0 + 1 | 0, tmp$_0) : (tmp$_1 = angleIndex, angleIndex = tmp$_1 + 1 | 0, tmp$_1);
          }
        }
        closure$updateTriangles();
      }
      return null;
    };
  }
  function main$lambda_2(closure$angleModeSelection, closure$drawer, closure$updateTriangles) {
    return function (it) {
      closure$drawer.isRadians = equals(closure$angleModeSelection.value, 'Radians');
      closure$updateTriangles();
      return null;
    };
  }
  function main$fitScreen(closure$screen) {
    return function () {
      closure$screen.width = roundToInt(ensureNotNull(closure$screen.parentElement).clientWidth * 0.75);
      closure$screen.height = roundToInt(closure$screen.width * 0.5);
    };
  }
  function main$lambda_3(closure$fitScreen, closure$updateTriangles) {
    return function (it) {
      closure$fitScreen();
      closure$updateTriangles();
      return null;
    };
  }
  var collectionSizeOrDefault = Kotlin.kotlin.collections.collectionSizeOrDefault_ba2ldo$;
  var ArrayList_init = Kotlin.kotlin.collections.ArrayList_init_ww73n8$;
  var copyToArray = Kotlin.kotlin.collections.copyToArray;
  function main(args) {
    var tmp$, tmp$_0, tmp$_1, tmp$_2, tmp$_3, tmp$_4;
    var screen = Kotlin.isType(tmp$ = document.getElementById('screen'), HTMLCanvasElement) ? tmp$ : throwCCE();
    var drawer = new TriangleDrawer(Kotlin.isType(tmp$_0 = screen.getContext('2d'), CanvasRenderingContext2D) ? tmp$_0 : throwCCE());
    var degreesBox = Kotlin.isType(tmp$_1 = document.getElementById('degreesBox'), HTMLInputElement) ? tmp$_1 : throwCCE();
    var radiansBox = Kotlin.isType(tmp$_2 = document.getElementById('radiansBox'), HTMLInputElement) ? tmp$_2 : throwCCE();
    degreesBox.oninput = main$lambda(degreesBox, radiansBox);
    radiansBox.oninput = main$lambda_0(radiansBox, degreesBox);
    var stringParts = ['a', 'b', 'c', 'A', 'B', 'C'];
    var typeIO = Kotlin.isType(tmp$_3 = document.getElementById('triangleType'), HTMLInputElement) ? tmp$_3 : throwCCE();
    var $receiver = asList(document.getElementsByClassName('componentSelect'));
    var destination = ArrayList_init(collectionSizeOrDefault($receiver, 10));
    var tmp$_5;
    tmp$_5 = $receiver.iterator();
    while (tmp$_5.hasNext()) {
      var item = tmp$_5.next();
      var tmp$_6;
      destination.add_11rb$(Kotlin.isType(tmp$_6 = item, HTMLSelectElement) ? tmp$_6 : throwCCE());
    }
    var componentDropdowns = destination;
    var angleModeSelection = Kotlin.isType(tmp$_4 = document.getElementById('angleMode'), HTMLSelectElement) ? tmp$_4 : throwCCE();
    var $receiver_0 = new IntRange(0, 2);
    var destination_0 = ArrayList_init(collectionSizeOrDefault($receiver_0, 10));
    var tmp$_7;
    tmp$_7 = $receiver_0.iterator();
    while (tmp$_7.hasNext()) {
      var item_0 = tmp$_7.next();
      var tmp$_8;
      destination_0.add_11rb$(Kotlin.isType(tmp$_8 = document.getElementById('input' + item_0), HTMLInputElement) ? tmp$_8 : throwCCE());
    }
    var inputBoxes = copyToArray(destination_0);
    var updateTriangles = main$updateTriangles(inputBoxes, componentDropdowns, angleModeSelection, drawer);
    var tmp$_9;
    for (tmp$_9 = 0; tmp$_9 !== inputBoxes.length; ++tmp$_9) {
      var element = inputBoxes[tmp$_9];
      element.oninput = main$lambda$lambda(updateTriangles);
    }
    var tmp$_10, tmp$_0_0;
    var index = 0;
    tmp$_10 = componentDropdowns.iterator();
    while (tmp$_10.hasNext()) {
      var item_1 = tmp$_10.next();
      var index_0 = (tmp$_0_0 = index, index = tmp$_0_0 + 1 | 0, tmp$_0_0);
      var tmp$_11, tmp$_12;
      for (tmp$_11 = 0; tmp$_11 !== stringParts.length; ++tmp$_11) {
        var letter = stringParts[tmp$_11];
        var option = Kotlin.isType(tmp$_12 = document.createElement('OPTION'), HTMLOptionElement) ? tmp$_12 : throwCCE();
        option.value = letter;
        option.text = letter;
        item_1.add(option);
      }
      item_1.selectedIndex = index_0;
      item_1.oninput = main$lambda$lambda_0(componentDropdowns, typeIO, updateTriangles);
    }
    typeIO.value = 'SSS';
    typeIO.oninput = main$lambda_1(typeIO, componentDropdowns, updateTriangles);
    angleModeSelection.oninput = main$lambda_2(angleModeSelection, drawer, updateTriangles);
    var fitScreen = main$fitScreen(screen);
    window.onresize = main$lambda_3(fitScreen, updateTriangles);
    fitScreen();
  }
  function hasBeenInitialized$lambda(a) {
    return a > 0;
  }
  var hasBeenInitialized;
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
  function TriangleDrawer(renderer) {
    this.renderer = renderer;
    this.aColor_0 = 'blue';
    this.bColor_0 = 'red';
    this.cColor_0 = 'orange';
    this.colorMap_0 = hashMapOf([to(0, this.aColor_0), to(1, this.bColor_0), to(2, this.cColor_0)]);
    this.isRadians = true;
    this.triangle_s3dj5s$_0 = null;
  }
  Object.defineProperty(TriangleDrawer.prototype, 'triangle', {
    get: function () {
      return this.triangle_s3dj5s$_0;
    },
    set: function (value) {
      if (value != null && value.isValid()) {
        this.triangle_s3dj5s$_0 = value;
        this.drawSolutions_0();
      }
       else {
        this.clearScreen_0();
        this.triangle_s3dj5s$_0 = null;
      }
    }
  });
  TriangleDrawer.prototype.clearScreen_0 = function () {
    this.renderer.clearRect(0.0, 0.0, this.renderer.canvas.width, this.renderer.canvas.height);
  };
  function TriangleDrawer$drawSolutions$drawTriangle$drawLine(this$TriangleDrawer) {
    return function (color, x0, y0, x1, y1) {
      this$TriangleDrawer.renderer.strokeStyle = color;
      this$TriangleDrawer.renderer.beginPath();
      this$TriangleDrawer.renderer.moveTo(x0, y0);
      this$TriangleDrawer.renderer.lineTo(x1, y1);
      this$TriangleDrawer.renderer.stroke();
      this$TriangleDrawer.renderer.closePath();
    };
  }
  function TriangleDrawer$drawSolutions$drawTriangle$drawDot(this$TriangleDrawer) {
    return function (color, x0, y0) {
      this$TriangleDrawer.renderer.fillStyle = color;
      this$TriangleDrawer.renderer.beginPath();
      this$TriangleDrawer.renderer.arc(x0, y0, this$TriangleDrawer.renderer.lineWidth, 0.0, 2 * math.PI);
      this$TriangleDrawer.renderer.fill();
      this$TriangleDrawer.renderer.closePath();
    };
  }
  function TriangleDrawer$drawSolutions$drawTriangle$formatString(closure$truncateLength) {
    var Math_0 = Math;
    return function closure$formatString(toFormat) {
      var tmp$;
      var numLength = toFormat.toString().length;
      var tmp$_0 = toFormat.toString();
      var a = numLength - 1 | 0;
      var b = closure$truncateLength + 1 | 0;
      var number = substring(tmp$_0, new IntRange(0, Math_0.max(a, b)));
      if (endsWith(number, '0') && !endsWith(number, '.0'))
        tmp$ = closure$formatString(toDouble(substring(number, until(0, number.length))));
      else if (number.length >= (closure$truncateLength + 1 | 0))
        if (number.length >= (closure$truncateLength + 2 | 0) && number.charCodeAt(closure$truncateLength + 1 | 0) !== 46 && toInt(String.fromCharCode(number.charCodeAt(closure$truncateLength + 1 | 0))) >= 5)
          tmp$ = closure$formatString(toDouble(substring(number, until(0, closure$truncateLength)) + toString((number.charCodeAt(closure$truncateLength) | 0) + 1 | 0)));
        else if (toFormat > toDouble(repeat('9', closure$truncateLength - 1 | 0))) {
          var power = indexOf(number, '.') - 1 | 0;
          var truncIndex = closure$truncateLength - power.toString().length - 1 | 0;
          var $receiver = number.charCodeAt(0);
          var tmp$_1 = String.fromCharCode($receiver) + '.';
          var $receiver_0 = replace(number, '.', '');
          var endIndex = truncIndex - 1 | 0;
          tmp$ = tmp$_1 + $receiver_0.substring(1, endIndex) + String.fromCharCode(toBoxedChar(toChar(number.charCodeAt(truncIndex - 1 | 0) + (toInt(String.fromCharCode(number.charCodeAt(truncIndex))) >= 5 ? 1 : 0)))) + 'E' + toString(power);
        }
         else
          tmp$ = substring(number, new IntRange(0, closure$truncateLength));
      else
        tmp$ = number + repeat(' ', closure$truncateLength + 1 - number.length | 0);
      return tmp$;
    };
  }
  function TriangleDrawer$drawSolutions$drawTriangle(this$TriangleDrawer) {
    return function (triangleToRepresent, x, y, width, height) {
      var tmp$, tmp$_0, tmp$_1;
      var sides = sliceArray(triangleToRepresent.sides, new IntRange(0, 3));
      var angles = sliceArray(triangleToRepresent.angles, new IntRange(0, 3));
      var indices = copyToArray(toList(new IntRange(0, 2)));
      for (var i = 2; i >= 0; i--) {
        for (var j = 1; j <= i; j++) {
          if (sides[j - 1 | 0] > sides[j]) {
            var $receiver = sides[j - 1 | 0];
            sides[j - 1 | 0] = sides[j];
            sides[j] = $receiver;
            var $receiver_0 = angles[j - 1 | 0];
            angles[j - 1 | 0] = angles[j];
            angles[j] = $receiver_0;
            var $receiver_1 = indices[j - 1 | 0];
            indices[j - 1 | 0] = indices[j];
            indices[j] = $receiver_1;
          }
        }
      }
      var triangleWidth = height < width ? height : width - 10 | 0;
      var tmp$_2 = sides[1];
      var x_0 = angles[0];
      var triangleHeight = numberToInt(tmp$_2 * Math_0.sin(x_0) * triangleWidth / sides[2]);
      var leftX = x + ((width - triangleWidth | 0) / 2 | 0) | 0;
      var rightX = leftX + triangleWidth | 0;
      var bottomY = y + ((height - triangleHeight | 0) / 2 | 0) + triangleHeight | 0;
      var x_1 = angles[1];
      var meetingX = leftX + numberToInt(Math_0.cos(x_1) * sides[0] * triangleWidth / sides[2]) | 0;
      var meetingY = bottomY - triangleHeight | 0;
      this$TriangleDrawer.renderer.lineWidth = triangleWidth / 200.0;
      var drawLine = TriangleDrawer$drawSolutions$drawTriangle$drawLine(this$TriangleDrawer);
      drawLine(this$TriangleDrawer.colorMap_0.get_11rb$(indices[2]), leftX, bottomY, rightX, bottomY);
      drawLine(this$TriangleDrawer.colorMap_0.get_11rb$(indices[0]), leftX, bottomY, meetingX, meetingY);
      drawLine(this$TriangleDrawer.colorMap_0.get_11rb$(indices[1]), rightX, bottomY, meetingX, meetingY);
      var drawDot = TriangleDrawer$drawSolutions$drawTriangle$drawDot(this$TriangleDrawer);
      drawDot(this$TriangleDrawer.colorMap_0.get_11rb$(indices[2]), meetingX, meetingY);
      drawDot(this$TriangleDrawer.colorMap_0.get_11rb$(indices[0]), rightX, bottomY);
      drawDot(this$TriangleDrawer.colorMap_0.get_11rb$(indices[1]), leftX, bottomY);
      this$TriangleDrawer.renderer.font = (triangleWidth / 35 | 0).toString() + 'px Courier New';
      var stringParts = ['a', 'b', 'c', 'A', 'B', 'C'];
      var truncateLength = 5;
      var lineSpacing = (2 * triangleWidth | 0) / 35 | 0;
      var formatString = TriangleDrawer$drawSolutions$drawTriangle$formatString(truncateLength);
      for (var i_0 = 0; i_0 <= 2; i_0++) {
        this$TriangleDrawer.renderer.fillStyle = this$TriangleDrawer.colorMap_0.get_11rb$(i_0);
        tmp$_1 = this$TriangleDrawer.renderer;
        tmp$_0 = stringParts[i_0] + ' = ' + formatString(triangleToRepresent.sides[i_0]) + ' ' + stringParts[i_0 + 3 | 0] + ' = ';
        if (this$TriangleDrawer.isRadians) {
          tmp$ = triangleToRepresent.angles[i_0];
        }
         else {
          tmp$ = asDegrees(triangleToRepresent.angles[i_0]);
        }
        tmp$_1.fillText(tmp$_0 + formatString(tmp$), x + lineSpacing / 2.0, y + (i_0 + 1.0) * lineSpacing);
      }
      this$TriangleDrawer.renderer.fillStyle = '#000000';
      this$TriangleDrawer.renderer.fillText(repeat(' ', (19 - 12 | 0) / 2 | 0) + 'Area = ' + formatString(triangleToRepresent.area()), x + lineSpacing / 2.0, y + 4.0 * lineSpacing);
    };
  }
  TriangleDrawer.prototype.drawSolutions_0 = function () {
    this.clearScreen_0();
    var solutions = ensureNotNull(this.triangle).solutions();
    var drawTriangle = TriangleDrawer$drawSolutions$drawTriangle(this);
    if (solutions.length === 2) {
      drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height / 2 | 0);
      drawTriangle(solutions[1], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop + (this.renderer.canvas.height / 2 | 0) | 0, this.renderer.canvas.width, this.renderer.canvas.height / 2 | 0);
    }
     else {
      drawTriangle(solutions[0], this.renderer.canvas.clientLeft, this.renderer.canvas.clientTop, this.renderer.canvas.width, this.renderer.canvas.height);
    }
  };
  TriangleDrawer.$metadata$ = {
    kind: Kind_CLASS,
    simpleName: 'TriangleDrawer',
    interfaces: []
  };
  var package$com = _.com || (_.com = {});
  var package$saurabhtotey = package$com.saurabhtotey || (package$com.saurabhtotey = {});
  var package$trianglesolver = package$saurabhtotey.trianglesolver || (package$saurabhtotey.trianglesolver = {});
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
  package$trianglesolver.TriangleDrawer = TriangleDrawer;
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
